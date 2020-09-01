
package acme.features.patron.creditCardForPatron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCards.CreditCardForPatron;
import acme.entities.roles.Patron;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service

public class PatronCreditCardForPatronCreateService implements AbstractCreateService<Patron, CreditCardForPatron> {

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
		request.bind(entity, errors, "patron", "banner");
	}

	@Override
	public void unbind(final Request<CreditCardForPatron> request, final CreditCardForPatron entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "holder", "number", "brand", "month", "year", "cvv");

	}

	@Override
	public CreditCardForPatron instantiate(final Request<CreditCardForPatron> request) {
		CreditCardForPatron res;
		res = new CreditCardForPatron();

		int idPatron = request.getPrincipal().getActiveRoleId();
		Patron p = this.repository.findOnePatronByUserAccountId(idPatron);
		res.setPatron(p);

		return res;
	}

	@Override
	public void validate(final Request<CreditCardForPatron> request, final CreditCardForPatron entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<CreditCardForPatron> request, final CreditCardForPatron entity) {
		assert request != null;
		assert entity != null;

		int patronId = request.getPrincipal().getActiveRoleId();
		Patron patron = this.repository.findOnePatronByUserAccountId(patronId);
		patron.setCard(entity);
		this.repository.save(patron);

		this.repository.save(entity);
	}

}
