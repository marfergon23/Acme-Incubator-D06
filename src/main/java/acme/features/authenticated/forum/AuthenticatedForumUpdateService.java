
package acme.features.authenticated.forum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedForumUpdateService implements AbstractUpdateService<Authenticated, Forum> {

	@Autowired
	private AuthenticatedForumRepository repository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		int idForum;
		boolean result = false;

		idForum = request.getModel().getInteger("forumId");
		Forum forum = this.repository.findOneById(idForum);
		String[] users = forum.getUsers().split(",");
		List<String> userList = Arrays.asList(users);
		for (String s : userList) {
			if (s.equals(principal.getUsername())) {
				result = true;
				break;
			}
		}

		return result;
	}

	@Override
	public void bind(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "users");
	}

	@Override
	public Forum findOne(final Request<Forum> request) {
		assert request != null;

		Forum result;
		int id;

		id = request.getModel().getInteger("forumId");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean exists = false;

		if (!errors.hasErrors("users")) {
			String[] users = entity.getUsers().split(",");
			List<String> userList = Arrays.asList(users);
			List<String> userNames = new ArrayList<>();
			Collection<UserAccount> userAccounts = this.repository.findAllUserAccounts();
			for (UserAccount ua : userAccounts) {
				userNames.add(ua.getUsername());
			}
			for (String u : userList) {
				for (String name : userNames) {
					if (u.equals(name)) {
						exists = true;
					} else {
						exists = false;
					}
					if (exists == true) {
						break;
					}
				}
			}
			errors.state(request, exists, "users", "authenticated.forum.error.users");
		}
	}

	@Override
	public void update(final Request<Forum> request, final Forum entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
