
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
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurInvestmentRoundCreateService implements AbstractCreateService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		return true;
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

		request.unbind(entity, model, "ticker", "moment", "kindRound", "title", "description", "amountMoney", "link");
	}

	@Override
	public InvestmentRound instantiate(final Request<InvestmentRound> request) {
		InvestmentRound result;
		result = new InvestmentRound();

		int id;
		Date moment;
		Entrepreneur entrepreneur;
		Principal principal;

		principal = request.getPrincipal();
		id = principal.getActiveRoleId();
		entrepreneur = this.repository.findOneEntrepreneurById(id);
		result.setEntrepreneur(entrepreneur);

		moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);

		result.setFinalMode(false);

		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Collection<String> tickers = this.repository.findAllTickers();

		Customisation customisation = this.repository.findCustomisation();
		String[] CustomisationParameter;
		Integer n = 0;

		// Tickers repetidos
		if (!errors.hasErrors("ticker")) {
			errors.state(request, !tickers.contains(entity.getTicker()), "ticker", "entrepreneur.investment-round.form.error.tickerRepetido");
		}

		// Ticker incorrecto
		if (!errors.hasErrors("ticker")) {
			List<String> res = new ArrayList<>();
			Date moment = new Date(System.currentTimeMillis() - 1);
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

	}

	@Override
	public void create(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		entity.setFinalMode(false);

		this.repository.save(entity);
	}

}
