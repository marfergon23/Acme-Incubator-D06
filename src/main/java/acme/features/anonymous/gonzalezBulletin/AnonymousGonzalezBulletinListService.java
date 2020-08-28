
package acme.features.anonymous.gonzalezBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.GonzalezBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousGonzalezBulletinListService implements AbstractListService<Anonymous, GonzalezBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousGonzalezBulletinRepository repository;


	@Override
	public boolean authorise(final Request<GonzalezBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<GonzalezBulletin> request, final GonzalezBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "movie", "director", "moment");

	}

	@Override
	public Collection<GonzalezBulletin> findMany(final Request<GonzalezBulletin> request) {
		assert request != null;

		Collection<GonzalezBulletin> result = this.repository.findMany();

		return result;
	}

}
