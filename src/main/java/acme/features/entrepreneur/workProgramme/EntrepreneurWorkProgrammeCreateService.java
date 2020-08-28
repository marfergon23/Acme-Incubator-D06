
package acme.features.entrepreneur.workProgramme;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.entities.workProgrammes.WorkProgramme;
import acme.features.entrepreneur.investmentRound.EntrepreneurInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurWorkProgrammeCreateService implements AbstractCreateService<Entrepreneur, WorkProgramme> {

	@Autowired
	EntrepreneurWorkProgrammeRepository		repository;

	@Autowired
	EntrepreneurInvestmentRoundRepository	investmentRoundRepository;


	@Override
	public boolean authorise(final Request<WorkProgramme> request) {
		assert request != null;
		boolean result;

		int id;
		InvestmentRound investmentRound;
		Entrepreneur entrepreneur;
		Principal principal;

		principal = request.getPrincipal();
		id = request.getModel().getInteger("investmentRoundId");
		investmentRound = this.investmentRoundRepository.findOneInvestmentRoundById(id);
		entrepreneur = investmentRound.getEntrepreneur();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();
		if (investmentRound.getFinalMode() == true) {
			result = false;
		}
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

		model.setAttribute("investmentRoundId", entity.getInvestmentRound().getId());
		request.unbind(entity, model, "title", "moment", "deadline", "budget");
	}

	@Override
	public WorkProgramme instantiate(final Request<WorkProgramme> request) {
		WorkProgramme result;
		result = new WorkProgramme();

		int id = request.getModel().getInteger("investmentRoundId");
		Date moment;
		InvestmentRound investmentRound;

		investmentRound = this.investmentRoundRepository.findOneInvestmentRoundById(id);
		result.setInvestmentRound(investmentRound);

		moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);

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

		Double sumBudget;

		Boolean superaMoney;

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
		int cont = this.investmentRoundRepository.countWorkProgrammesByInvestmentRoundId(entity.getInvestmentRound().getId());

		if (cont != 0) {

			if (!errors.hasErrors("budget")) {
				superaMoney = true;
				sumBudget = this.investmentRoundRepository.sumBudgetWorkProgramme(entity.getInvestmentRound().getId());
				double actualBudget = entity.getBudget().getAmount();
				double res = sumBudget + actualBudget;
				if (res > this.investmentRoundRepository.findOneInvestmentRoundById(entity.getInvestmentRound().getId()).getAmountMoney().getAmount()) {
					superaMoney = false;
				}
				errors.state(request, superaMoney, "budget", "entrepreneur.investment-round.form.error.dineroIncorrecto");
			}
		}

		else if (cont == 0) {

			if (!errors.hasErrors("budget")) {
				superaMoney = true;
				double actualBudget = entity.getBudget().getAmount();
				if (actualBudget > this.investmentRoundRepository.findOneInvestmentRoundById(entity.getInvestmentRound().getId()).getAmountMoney().getAmount()) {
					superaMoney = false;
				}
				errors.state(request, superaMoney, "budget", "entrepreneur.investment-round.form.error.dineroIncorrecto");
			}
		}

	}

	@Override
	public void create(final Request<WorkProgramme> request, final WorkProgramme entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		int cont = this.investmentRoundRepository.countWorkProgrammesByInvestmentRoundId(entity.getInvestmentRound().getId());

		if (cont != 0) {

			double sumBudget = this.investmentRoundRepository.sumBudgetWorkProgramme(entity.getInvestmentRound().getId());
			double actualBudget = entity.getBudget().getAmount();
			double res = sumBudget + actualBudget;
			if (res == this.investmentRoundRepository.findOneInvestmentRoundById(entity.getInvestmentRound().getId()).getAmountMoney().getAmount()) {
				entity.getInvestmentRound().setFinalMode(true);
				this.repository.save(entity.getInvestmentRound());
			}
		}

		else if (cont == 0) {

			double actualBudget = entity.getBudget().getAmount();
			if (actualBudget == this.investmentRoundRepository.findOneInvestmentRoundById(entity.getInvestmentRound().getId()).getAmountMoney().getAmount()) {
				entity.getInvestmentRound().setFinalMode(true);
				this.repository.save(entity.getInvestmentRound());
			}
		}

		this.repository.save(entity);
	}

}
