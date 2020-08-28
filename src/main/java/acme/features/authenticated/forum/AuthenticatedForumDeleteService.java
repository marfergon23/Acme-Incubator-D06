
package acme.features.authenticated.forum;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.entities.messages.Message;
import acme.features.authenticated.message.AuthenticatedMessageRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedForumDeleteService implements AbstractDeleteService<Authenticated, Forum> {

	@Autowired
	private AuthenticatedForumRepository	repository;

	@Autowired
	private AuthenticatedMessageRepository	messageRepository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		int idForum;
		boolean result = false;

		idForum = request.getModel().getInteger("id");
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

		request.unbind(entity, model, "title", "users");
	}

	@Override
	public Forum findOne(final Request<Forum> request) {
		assert request != null;

		Forum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Forum> request, final Forum entity) {
		assert request != null;
		assert entity != null;
		Collection<Message> messages = this.repository.findManyMessagesByForumId(entity.getId());
		for (Message m : messages) {
			this.messageRepository.delete(m);
		}
		this.repository.delete(entity);
	}

}
