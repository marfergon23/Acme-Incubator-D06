
package acme.features.authenticated.forum;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedForumShowService implements AbstractShowService<Authenticated, Forum> {

	@Autowired
	private AuthenticatedForumRepository repository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		Boolean result = false;
		int id = request.getModel().getInteger("id");
		Forum forum = this.repository.findOneById(id);
		List<String> usernames = Arrays.asList(forum.getUsers().split(","));
		if (usernames.contains(request.getPrincipal().getUsername())) {
			result = true;
		}
		return result;
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "users", "investmentRound.title");
	}

	@Override
	public Forum findOne(final Request<Forum> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		Forum result = this.repository.findOneById(id);

		return result;
	}

}
