
package acme.features.authenticated.overture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overtures.Overture;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedOvertureShowService implements AbstractShowService<Authenticated, Overture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedOvertureRepository repository;


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;
		Boolean res = false;
		Principal principal = request.getPrincipal();
		if (principal.isAuthenticated()) {
			res = true;
		}
		return res;
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "moment", "title", "deadline", "description", "email", "minMoney", "maxMoney");
	}

	@Override
	public Overture findOne(final Request<Overture> request) {
		assert request != null;
		int id = request.getModel().getInteger("id");
		Overture res = this.repository.findOne(id);
		return res;
	}

}
