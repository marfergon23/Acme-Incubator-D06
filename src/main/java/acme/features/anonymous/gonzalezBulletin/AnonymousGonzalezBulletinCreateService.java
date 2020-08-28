
package acme.features.anonymous.gonzalezBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.GonzalezBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousGonzalezBulletinCreateService implements AbstractCreateService<Anonymous, GonzalezBulletin> {

	// Internal state -------------------------------------------------------------

	@Autowired
	AnonymousGonzalezBulletinRepository repository;


	@Override
	public boolean authorise(final Request<GonzalezBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<GonzalezBulletin> request, final GonzalezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<GonzalezBulletin> request, final GonzalezBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "movie", "director");
	}

	@Override
	public GonzalezBulletin instantiate(final Request<GonzalezBulletin> request) {
		assert request != null;

		GonzalezBulletin result = new GonzalezBulletin();

		result.setMovie("Interstellar");
		result.setDirector("Christopher Nolan");

		return result;
	}

	@Override
	public void validate(final Request<GonzalezBulletin> request, final GonzalezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<GonzalezBulletin> request, final GonzalezBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
