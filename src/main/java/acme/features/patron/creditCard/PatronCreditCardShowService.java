
package acme.features.patron.creditCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class PatronCreditCardShowService implements AbstractShowService<Patron, CreditCard> {

	@Autowired
	PatronCreditCardRepository Repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		Boolean res = false;
		int patronId = request.getPrincipal().getActiveRoleId();
		Patron patron = this.Repository.findOnePatronByUserAccountId(patronId);
		int cdId = request.getModel().getInteger("id");
		CreditCard cd = this.Repository.findOneCreditCardById(cdId);
		if (cd.getPatron().getId() == patron.getId()) {
			res = true;
		}

		return res;
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "holder", "number", "brand", "month", "year", "cvv");
		Integer banner = request.getModel().getInteger("banner");
		model.setAttribute("banner", banner);
	}

	@Override
	public CreditCard findOne(final Request<CreditCard> request) {
		assert request != null;
		int id = request.getModel().getInteger("id");
		CreditCard res = this.Repository.findOneCreditCardById(id);
		return res;
	}

}
