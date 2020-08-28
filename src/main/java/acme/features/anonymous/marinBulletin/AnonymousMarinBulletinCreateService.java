package acme.features.anonymous.marinBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.MarinBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousMarinBulletinCreateService implements AbstractCreateService<Anonymous, MarinBulletin> {

	// Internal state -------------------------------------------------------------

	@Autowired
	AnonymousMarinBulletinRepository repository;


	@Override
	public boolean authorise(final Request<MarinBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public MarinBulletin instantiate(final Request<MarinBulletin> request) {
		assert request != null;

		MarinBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new MarinBulletin();
		result.setWeb("http://game.es");
		result.setDescription("Página de una tienda de videojuegos.");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<MarinBulletin> request, final MarinBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "web", "description");
	}

	@Override
	public void bind(final Request<MarinBulletin> request, final MarinBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<MarinBulletin> request, final MarinBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<MarinBulletin> request, final MarinBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}
}