
package acme.features.administrator.overtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overtures.Overture;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorOvertureShowService implements AbstractShowService<Administrator, Overture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorOvertureRepository repository;


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;

		return true;
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
		Overture res = this.repository.findOneById(id);
		return res;
	}

}
