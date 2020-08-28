
package acme.features.entrepreneur.investmentRound;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentRoundUpdateService implements AbstractUpdateService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;
		boolean result;

		int id;
		InvestmentRound investmentRound;
		Entrepreneur entrepreneur;
		Principal principal;

		id = request.getModel().getInteger("id");
		investmentRound = this.repository.findOneInvestmentRoundById(id);
		entrepreneur = investmentRound.getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "moment", "kindRound", "title", "description", "amountMoney", "link", "finalMode");
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;
		InvestmentRound result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneInvestmentRoundById(id);
		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Collection<String> tickers = this.repository.findAllTickers();
		String tickerUpdate = this.repository.findOneTickerById(request.getModel().getInteger("id"));
		tickers.remove(tickerUpdate);

		Customisation customisation = this.repository.findCustomisation();
		String[] CustomisationParameter;
		Integer n = 0;

		Boolean superaMoney;
		Double sumaBudget;

		// Tickers repetidos
		if (!errors.hasErrors("ticker")) {
			errors.state(request, !tickers.contains(entity.getTicker()), "ticker", "entrepreneur.investment-round.form.error.tickerRepetido");
		}

		// Ticker incorrecto
		if (!errors.hasErrors("ticker")) {
			List<String> res = new ArrayList<>();
			Date moment = this.repository.findOneInvestmentRoundById(request.getModel().getInteger("id")).getMoment();
			Integer year = moment.getYear() + 1900;

			res.add(entity.getTicker().substring(0, entity.getTicker().indexOf("-")));
			res.add(entity.getTicker().substring(entity.getTicker().indexOf("-") + 1, entity.getTicker().indexOf("-") + 3));
			res.add(entity.getTicker().substring(entity.getTicker().indexOf("-") + 4, entity.getTicker().length()));

			boolean result = res.get(0).matches("[A-Z ]+") && (res.get(0).equals(entity.getEntrepreneur().getActivitySector().substring(0, 1).toUpperCase()) || res.get(0).equals(entity.getEntrepreneur().getActivitySector().substring(0, 2).toUpperCase())
				|| res.get(0).equals(entity.getEntrepreneur().getActivitySector().substring(0, 3).toUpperCase())) && res.get(1).equals(year.toString().substring(2)) && res.get(2).matches("^[0-9]{6}$");

			errors.state(request, result, "ticker", "entrepreneur.investment-round.form.error.tickerIncorrecto");
		}

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
				errors.state(request, n <= spam, "title", "entrepreneur.investment-round.form.error.tituloConSpam");

				if (n > spam) {
					break;
				}
			}

		}

		// Spam descripción
		if (!errors.hasErrors("description")) {

			Double spam = Double.valueOf(entity.getDescription().split(" ").length) * customisation.getThreshold() / 100.0;
			CustomisationParameter = customisation.getSpamwords().split(",");

			for (String s : CustomisationParameter) {
				String l = entity.getDescription().toLowerCase();
				int i = l.indexOf(s);
				while (i != -1) {
					n++;
					l = l.substring(i + 1);
					i = l.indexOf(s);
				}
				errors.state(request, n <= spam, "description", "entrepreneur.investment-round.form.error.descripcionConSpam");

				if (n > spam) {
					break;
				}
			}

		}

		// Dinero incorrecto
		if (!errors.hasErrors("amountMoney")) {
			errors.state(request, (entity.getAmountMoney().getCurrency().equals("EUR") || entity.getAmountMoney().getCurrency().equals("€")) && entity.getAmountMoney().getAmount() > 0, "amountMoney",
				"entrepreneur.investment-round.form.error.dineroIncorrecto");
		}

		// Dinero incorrecto
		int cont = 0;
		cont = this.repository.countWorkProgrammesByInvestmentRoundId(entity.getId());
		if (cont != 0) {

			if (!errors.hasErrors("amountMoney")) {
				superaMoney = true;
				sumaBudget = this.repository.sumBudgetWorkProgramme(entity.getId());
				double actualAmount = entity.getAmountMoney().getAmount();
				if (actualAmount < sumaBudget) {
					superaMoney = false;
				}
				errors.state(request, superaMoney, "amountMoney", "entrepreneur.investment-round.form.error.dineroIncorrecto");
			}
		}

	}

	@Override
	public void update(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		int cont = 0;
		cont = this.repository.countWorkProgrammesByInvestmentRoundId(entity.getId());
		if (cont != 0) {

			double sumaBudget = this.repository.sumBudgetWorkProgramme(entity.getId());
			double actualAmount = entity.getAmountMoney().getAmount();
			if (actualAmount == sumaBudget) {
				entity.setFinalMode(true);
			}
		}

		this.repository.save(entity);
	}

}
