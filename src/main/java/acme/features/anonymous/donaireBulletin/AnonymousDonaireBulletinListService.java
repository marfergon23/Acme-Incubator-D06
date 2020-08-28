
package acme.features.anonymous.donaireBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.DonaireBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousDonaireBulletinListService implements AbstractListService<Anonymous, DonaireBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousDonaireBulletinRepository repository;


	@Override
	public boolean authorise(final Request<DonaireBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<DonaireBulletin> request, final DonaireBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "moment");
	}

	@Override
	public Collection<DonaireBulletin> findMany(final Request<DonaireBulletin> request) {
		assert request != null;

		Collection<DonaireBulletin> result = this.repository.findMany();

		return result;
	}

}
