
package acme.features.authenticated.forum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedForumListMineService implements AbstractListService<Authenticated, Forum> {

	@Autowired
	private AuthenticatedForumRepository repository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		return request.getPrincipal().hasRole(Authenticated.class);
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title");
	}

	@Override
	public Collection<Forum> findMany(final Request<Forum> request) {
		assert request != null;

		Collection<Forum> result = new ArrayList<Forum>();
		Collection<Forum> forums = this.repository.findMany();
		String username = request.getPrincipal().getUsername();
		for (Forum f : forums) {
			List<String> usernames = Arrays.asList(f.getUsers().split(","));
			if (usernames.contains(username)) {
				result.add(f);
			}
		}
		return result;
	}

}
