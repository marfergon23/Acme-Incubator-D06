
package acme.features.authenticated.message;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.forums.Forum;
import acme.entities.messages.Message;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

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
		//	result = userList.contains(principal.getUsername());
		for (String s : userList) {
			if (s.equals(principal.getUsername())) {
				result = true;
				break;
			}
		}

		return result;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("forumId", request.getModel().getInteger("forumId"));
		request.unbind(entity, model, "title", "moment", "tags", "body");
	}

	@Override
	public Message instantiate(final Request<Message> request) {

		//		assert request != null;
		//
		//		Message result;
		//		Authenticated authenticated;
		//		int userAccountId;
		//		Principal principal;
		//		result = new Message();
		//
		//		int forumId = request.getModel().getInteger("forumid");
		//		Forum forum = this.repository.findForumById(forumId);
		//		result.setForum(forum);
		//
		//		Date moment = new Date(System.currentTimeMillis() - 1);
		//		result.setMoment(moment);
		//
		//		principal = request.getPrincipal();
		//		userAccountId = principal.getAccountId();
		//		authenticated = this.repository.findOneAuthenticatedByUserAccountId(userAccountId);
		//		result.setUser(authenticated);
		//		return result;

		assert request != null;
		Message result = new Message();

		int userId = request.getPrincipal().getActiveRoleId();
		Authenticated us = this.repository.findOneAuthenticatedById(userId);
		result.setUser(us);

		int forumId = request.getModel().getInteger("forumId");
		result.setForum(this.repository.findOneForumById(forumId));

		Date moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isConfirmed = request.getModel().getString("confirm").length() > 0 && request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "authenticated.message.form.error.must-confirm");

		Customisation cp = this.repository.findOneCustomisation();

		boolean titleHasErrors = errors.hasErrors("title");
		if (!titleHasErrors) {
			errors.state(request, !cp.isSpam(entity.getTitle()), "title", "authenticated.message.form.error.spam");
		}

		boolean bodyHasErrors = errors.hasErrors("body");
		if (!bodyHasErrors) {
			errors.state(request, !cp.isSpam(entity.getBody()), "body", "authenticated.message.form.error.spam");
		}

		boolean tagsHasErrors = errors.hasErrors("tags");
		if (!tagsHasErrors) {
			errors.state(request, !cp.isSpam(entity.getTags()), "tags", "authenticated.message.form.error.spam");
		}
	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);

	}

}
