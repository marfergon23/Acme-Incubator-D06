
package acme.features.anonymous.donaireBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.DonaireBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousDonaireBulletinCreateService implements AbstractCreateService<Anonymous, DonaireBulletin> {

	// Internal state -------------------------------------------------------------

	@Autowired
	AnonymousDonaireBulletinRepository repository;


	@Override
	public boolean authorise(final Request<DonaireBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<DonaireBulletin> request, final DonaireBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<DonaireBulletin> request, final DonaireBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text");
	}

	@Override
	public DonaireBulletin instantiate(final Request<DonaireBulletin> request) {
		assert request != null;

		DonaireBulletin result = new DonaireBulletin();
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		result.setAuthor("Carlos Ruiz Zafon");
		result.setText("La sombra del viento");
		result.setMoment(moment);
		return result;
	}

	@Override
	public void validate(final Request<DonaireBulletin> request, final DonaireBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}


	@Override
	public void create(final Request<DonaireBulletin> request, final DonaireBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
