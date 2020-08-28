
package acme.features.entrepreneur.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.applications.ApplicationStatus;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurApplicationUpdateService implements AbstractUpdateService<Entrepreneur, Application> {

	// Internal state ------------------------------------------------------------------
	@Autowired
	EntrepreneurApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int applicationId;
		Application application;
		Entrepreneur entrepreneur;
		Principal principal;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findOneById(applicationId);
		entrepreneur = application.getInvestmentRound().getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
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

		String referenceInvestmentRound = entity.getInvestmentRound().getTicker();
		model.setAttribute("referenceInvestmentRound", referenceInvestmentRound);
		String InvestmentRoundEntrepreneur = entity.getInvestmentRound().getEntrepreneur().getUserAccount().getUsername();
		model.setAttribute("InvestmentRoundEntrepreneur", InvestmentRoundEntrepreneur);

		request.unbind(entity, model, "ticker", "moment", "status", "statement", "moneyOffer", "rejectionJustification");

	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean statusHasErrors = errors.hasErrors("status");
		if (!statusHasErrors) {
			boolean rejectionJustificationHasErrors = errors.hasErrors("rejectionJustification");
			if (!rejectionJustificationHasErrors) {
				boolean hasRejectionJustification = entity.getStatus() != ApplicationStatus.REJECTED || entity.getStatus() == ApplicationStatus.REJECTED && entity.getRejectionJustification().length() > 0;
				errors.state(request, hasRejectionJustification, "rejectionJustification", "entrepreneur.application.form.error.rejectionJustification");
			}
		}

	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		result.setStatus(entity.getStatus());
		if (result.getStatus() == ApplicationStatus.REJECTED) {
			result.setRejectionJustification(entity.getRejectionJustification());
		}

		this.repository.save(result);

	}

}
