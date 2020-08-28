
package acme.features.entrepreneur.workProgramme;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.roles.Entrepreneur;
import acme.entities.workProgrammes.WorkProgramme;
import acme.features.entrepreneur.investmentRound.EntrepreneurInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurWorkProgrammeUpdateService implements AbstractUpdateService<Entrepreneur, WorkProgramme> {

	@Autowired
	EntrepreneurWorkProgrammeRepository		repository;

	@Autowired
	EntrepreneurInvestmentRoundRepository	investmentRoundRepository;


	@Override
	public boolean authorise(final Request<WorkProgramme> request) {
		assert request != null;
		boolean result;

		int id;
		WorkProgramme workProgramme;
		Entrepreneur entrepreneur;
		Principal principal;

		principal = request.getPrincipal();
		id = request.getModel().getInteger("id");
		workProgramme = this.repository.findOneWorkProgrammeById(id);
		entrepreneur = workProgramme.getInvestmentRound().getEntrepreneur();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<WorkProgramme> request, final WorkProgramme entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<WorkProgramme> request, final WorkProgramme entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "deadline", "budget");
	}

	@Override
	public WorkProgramme findOne(final Request<WorkProgramme> request) {
		assert request != null;
		WorkProgramme result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneWorkProgrammeById(id);
		return result;
	}

	@Override
	public void validate(final Request<WorkProgramme> request, final WorkProgramme entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Customisation customisation = this.investmentRoundRepository.findCustomisation();
		String[] CustomisationParameter;
		Integer n = 0;

		Calendar calendar;
		Date deadlineOK;

		Boolean superaMoney;
		Double sumBudget;

		// Spam título
		if (!errors.hasErrors("title")) {

			Double spam = Double.valueOf(entity.getTitle().split(" ").length) * customisation.getThreshold() / 100.0;
			CustomisationParameter = customisation.getSpamwords().split(",");

			for (String s : CustomisationParameter) {
				String l = entity.getTitle().toLowerCase();
				int i = l.indexOf(s);
				while (i != -1) {
					n++;
					l = l.substring(i + 1);
					i = l.indexOf(s);
				}
				errors.state(request, n <= spam, "title", "entrepreneur.work-programme.form.error.tituloConSpam");
				if (n > spam) {
					break;
				}
			}

		}

		// Fecha incorrecta
		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, +1);
			deadlineOK = calendar.getTime();
			errors.state(request, entity.getDeadline().after(deadlineOK), "deadline", "entrepreneur.work-programme.form.error.fechaIncorrecta");
		}

		// Dinero incorrecto
		if (!errors.hasErrors("budget")) {
			errors.state(request, (entity.getBudget().getCurrency().equals("EUR") || entity.getBudget().getCurrency().equals("€")) && entity.getBudget().getAmount() > 0, "budget", "entrepreneur.work-programme.form.error.dineroIncorrecto");
		}

		// Dinero incorrecto
		if (!errors.hasErrors("budget")) {
			superaMoney = true;
			sumBudget = this.investmentRoundRepository.sumBudgetWorkProgramme(entity.getInvestmentRound().getId());
			double viejoBudget = this.repository.findOneBudgetByWorkProgrammeId(entity.getId());
			double actualBudget = entity.getBudget().getAmount();
			double res = sumBudget + actualBudget - viejoBudget;
			if (res > this.investmentRoundRepository.findOneInvestmentRoundById(entity.getInvestmentRound().getId()).getAmountMoney().getAmount()) {
				superaMoney = false;
			}
			errors.state(request, superaMoney, "budget", "entrepreneur.investment-round.form.error.dineroIncorrecto");
		}

	}

	@Override
	public void update(final Request<WorkProgramme> request, final WorkProgramme entity) {
		assert request != null;
		assert entity != null;

		double sumBudget = this.investmentRoundRepository.sumBudgetWorkProgramme(entity.getInvestmentRound().getId());
		double viejoBudget = this.repository.findOneBudgetByWorkProgrammeId(entity.getId());
		double actualBudget = entity.getBudget().getAmount();
		double res = sumBudget + actualBudget - viejoBudget;
		if (res == this.investmentRoundRepository.findOneInvestmentRoundById(entity.getInvestmentRound().getId()).getAmountMoney().getAmount()) {
			entity.getInvestmentRound().setFinalMode(true);
			this.repository.save(entity.getInvestmentRound());
		}

		this.repository.save(entity);

	}

}
