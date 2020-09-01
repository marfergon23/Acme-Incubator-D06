
package acme.features.patron.creditCardForPatron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCards.CreditCardForPatron;
import acme.entities.roles.Patron;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class PatronCreditCardForPatronShowService implements AbstractShowService<Patron, CreditCardForPatron> {

	@Autowired
	PatronCreditCardForPatronRepository Repository;


	@Override
	public boolean authorise(final Request<CreditCardForPatron> request) {
		assert request != null;

		Boolean res = false;
		int patronId = request.getPrincipal().getActiveRoleId();
		Patron patron = this.Repository.findOnePatronByUserAccountId(patronId);
		int id = request.getModel().getInteger("id");
		CreditCardForPatron cd = this.Repository.findOneCreditCardForPatronById(id);
	
		if (cd.getPatron().getId() == patron.getId()) {
			res = true;
		}
		return res;
	}

	@Override
	public void unbind(final Request<CreditCardForPatron> request, final CreditCardForPatron entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "holder", "number", "brand", "month", "year", "cvv");
		Integer banner = request.getModel().getInteger("banner");
		model.setAttribute("banner", banner);
	}

	@Override
	public CreditCardForPatron findOne(final Request<CreditCardForPatron> request) {
		assert request != null;
		int id = request.getModel().getInteger("id");
		CreditCardForPatron res = this.Repository.findOneCreditCardForPatronById(id);
		return res;
	}

}
