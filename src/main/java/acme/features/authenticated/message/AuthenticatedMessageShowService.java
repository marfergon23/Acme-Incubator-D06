
package acme.features.authenticated.message;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageShowService implements AbstractShowService<Authenticated, Message> {

	@Autowired
	private AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		Boolean result = false;
		String principalUser = request.getPrincipal().getUsername();
		int messageid = request.getModel().getInteger("id");
		Forum f = this.repository.findOneById(messageid).getForum();

		String[] users = f.getUsers().split(",");
		List<String> userList = Arrays.asList(users);
		if (userList.contains(principalUser)) {
			result = true;
		}
		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "moment", "tags", "body");
	}

	@Override
	public Message findOne(final Request<Message> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		Message result = this.repository.findOneById(id);

		return result;
	}

}
