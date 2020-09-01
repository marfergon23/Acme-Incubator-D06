
package acme.features.patron.creditCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service

public class PatronCreditCardUpdateService implements AbstractUpdateService<Patron, CreditCard> {

	@Autowired
	PatronCreditCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		boolean result;
		Patron patron;
		Principal principal;

		int id = request.getModel().getInteger("creditCard");
		Banner b = this.repository.findBannerByCreditCardId(id);

		patron = this.repository.findOnePatronByUserAccountId(request.getPrincipal().getActiveRoleId());
		principal = request.getPrincipal();
		result = patron.getUserAccount().getId() == principal.getAccountId() && patron.getBanners().stream().anyMatch(a -> a.getId() == b.getId());

		return result;
	}

	@Override
	public void bind(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "patron", "banner");
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "holder", "number", "brand", "month","year", "cvv");
		
		int id = request.getModel().getInteger("creditCard");

		model.setAttribute("creditCard", id);
	}

	@Override
	public CreditCard findOne(final Request<CreditCard> request) {
		assert request != null;

		CreditCard result;
		int id = request.getModel().getInteger("creditCard");
		result = this.repository.findOneCreditCardById(id);

		return result;
	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}
}