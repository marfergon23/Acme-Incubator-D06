
package acme.features.authenticated.message;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListMineService implements AbstractListService<Authenticated, Message> {

	@Autowired
	private AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		int idForum;
		boolean result = false;

		idForum = request.getModel().getInteger("forumId");
		Forum forum = this.repository.findForumById(idForum);
		String[] users = forum.getUsers().split(",");
		List<String> userList = Arrays.asList(users);
		result = userList.contains(principal.getUsername());

		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "tags");
	}

	@Override
	public Collection<Message> findMany(final Request<Message> request) {
		assert request != null;

		Integer idForum = request.getModel().getInteger("forumId");
		Collection<Message> result = this.repository.findManyByForumId(idForum);

		return result;
	}

}
