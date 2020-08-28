
package acme.features.authenticated.bookKeeperRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bookKeeperRequest.BookKeeperRequest;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedBookKeeperRequestCreateService implements AbstractCreateService<Authenticated, BookKeeperRequest> {

	@Autowired
	private AuthenticatedBookKeeperRequestRepository repository;


	@Override
	public boolean authorise(final Request<BookKeeperRequest> request) {
		assert request != null;
		Boolean result = !request.getPrincipal().hasRole(Bookkeeper.class);
		return result;
	}

	@Override
	public void bind(final Request<BookKeeperRequest> request, final BookKeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<BookKeeperRequest> request, final BookKeeperRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Boolean pending = this.repository.findBookKeeperRequestByUserId(request.getPrincipal().getAccountId()) != null;
		model.setAttribute("pending", pending);
		request.unbind(entity, model, "firmName", "responsibilityStatement", "status");
	}

	@Override
	public BookKeeperRequest instantiate(final Request<BookKeeperRequest> request) {
		assert request != null;
		BookKeeperRequest result = new BookKeeperRequest();

		int userId = request.getPrincipal().getAccountId();
		UserAccount ua = this.repository.findUserAccountById(userId);
		result.setUserAccount(ua);
		return result;
	}

	@Override
	public void validate(final Request<BookKeeperRequest> request, final BookKeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<BookKeeperRequest> request, final BookKeeperRequest entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
