
package acme.features.investor.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.applications.ApplicationStatus;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.features.entrepreneur.investmentRound.EntrepreneurInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorApplicationCreateService implements AbstractCreateService<Investor, Application> {

	@Autowired
	InvestorApplicationRepository			repository;

	@Autowired
	EntrepreneurInvestmentRoundRepository	repositoryInvest;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		Boolean res = true;
		int irId = request.getModel().getInteger("investId");
		int invId = request.getPrincipal().getActiveRoleId();
		Integer numApplications = this.repository.findApplicationsByInvestmentRoundId(irId, invId);

		System.out.println("numero de aplicaciones o lo que falle: " + numApplications);

		if (numApplications != 0) {
			res = false;
		}

		InvestmentRound ir = this.repositoryInvest.findOneInvestmentRoundById(irId);
		if (!ir.getFinalMode()) {
			res = false;
		}

		return res;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("investId", entity.getInvestmentRound().getId());

		request.unbind(entity, model, "ticker", "moment");
		request.unbind(entity, model, "statement", "status", "moneyOffer", "investmentRound.ticker");
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;
		Application result;
		result = new Application();

		result.setStatus(ApplicationStatus.PENDING);

		int id;
		int idInv;
		Date moment;
		Entrepreneur entrepreneur;
		Investor investor;
		Principal principal;

		principal = request.getPrincipal();
		id = principal.getActiveRoleId();
		idInv = request.getModel().getInteger("investId");

		System.out.println("idInv " + idInv);
		InvestmentRound ir = this.repositoryInvest.findOneInvestmentRoundById(idInv);
		System.out.println("investment " + ir);
		entrepreneur = ir.getEntrepreneur();
		System.out.println("entrepreneur " + entrepreneur);
		investor = this.repository.findOneInvestorById(id);
		System.out.println("investor " + investor);
		moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);

		result.setEntrepreneur(entrepreneur);
		result.setInvestor(investor);
		result.setInvestmentRound(ir);
		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Collection<String> tickers = this.repository.findAllApplicationTickers();

		// Tickers repetidos
		if (!errors.hasErrors("ticker")) {
			errors.state(request, !tickers.contains(entity.getTicker()), "ticker", "investor.application.form.error.tickerRepetido");
		}

		// Ticker incorrecto
		if (!errors.hasErrors("ticker")) {
			List<String> res = new ArrayList<>();
			Date moment = new Date(System.currentTimeMillis() - 1);
			Integer year = moment.getYear() + 1900;

			res.add(entity.getTicker().substring(0, entity.getTicker().indexOf("-")));
			res.add(entity.getTicker().substring(entity.getTicker().indexOf("-") + 1, entity.getTicker().indexOf("-") + 3));
			res.add(entity.getTicker().substring(entity.getTicker().indexOf("-") + 4, entity.getTicker().length()));

			boolean result = res.get(0).matches("[A-Z ]+") && (res.get(0).equals(entity.getInvestor().getActivitySector().substring(0, 1).toUpperCase()) || res.get(0).equals(entity.getInvestor().getActivitySector().substring(0, 2).toUpperCase())
				|| res.get(0).equals(entity.getInvestor().getActivitySector().substring(0, 3).toUpperCase())) && res.get(1).equals(year.toString().substring(2)) && res.get(2).matches("^[0-9]{6}$");

			errors.state(request, result, "ticker", "investor.application.form.error.tickerIncorrecto");
		}

		// Dinero incorrecto
		if (!errors.hasErrors("moneyOffer")) {
			errors.state(request, entity.getMoneyOffer().getCurrency().equals("EUR") || entity.getMoneyOffer().getCurrency().equals("€"), "moneyOffer", "investor.application.form.error.dineroIncorrecto");
		}

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);
	}

}
