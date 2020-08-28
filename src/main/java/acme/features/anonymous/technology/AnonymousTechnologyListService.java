
package acme.features.anonymous.technology;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.technologies.Technology;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousTechnologyListService implements AbstractListService<Anonymous, Technology> {

	@Autowired
	AnonymousTechnologyRepository repository;


	@Override
	public boolean authorise(final Request<Technology> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Technology> request, final Technology entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "activitySector", "stars");
	}

	@Override
	public Collection<Technology> findMany(final Request<Technology> request) {
		assert request != null;
		Collection<Technology> result;
		result = this.repository.findManyAll();
		return result;
	}

}
