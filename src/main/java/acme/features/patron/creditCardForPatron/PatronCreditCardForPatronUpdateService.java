
package acme.features.patron.creditCardForPatron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import acme.entities.creditCards.CreditCardForPatron;
import acme.entities.roles.Patron;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service

public class PatronCreditCardForPatronUpdateService implements AbstractUpdateService<Patron, CreditCardForPatron> {

	@Autowired
	PatronCreditCardForPatronRepository repository;


	@Override
	public boolean authorise(final Request<CreditCardForPatron> request) {
		assert request != null;

		boolean result;
		Patron patron;
		Principal principal;

		patron = this.repository.findOnePatronByUserAccountId(request.getPrincipal().getActiveRoleId());
		principal = request.getPrincipal();
		result = patron.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void bind(final Request<CreditCardForPatron> request, final CreditCardForPatron entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "patron");
	}

	@Override
	public void unbind(final Request<CreditCardForPatron> request, final CreditCardForPatron entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "holder", "number", "brand", "month","year", "cvv");
		
	}

	@Override
	public CreditCardForPatron findOne(final Request<CreditCardForPatron> request) {
		assert request != null;

		CreditCardForPatron result;
		int id = request.getModel().getInteger("card");
		result = this.repository.findOneCreditCardForPatronById(id);

		return result;
	}

	@Override
	public void validate(final Request<CreditCardForPatron> request, final CreditCardForPatron entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<CreditCardForPatron> request, final CreditCardForPatron entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}
}