
package acme.features.authenticated.bookKeeperRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bookKeeperRequest.BookKeeperRequest;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedBookKeeperRequestShowService implements AbstractShowService<Authenticated, BookKeeperRequest> {

	@Autowired
	private AuthenticatedBookKeeperRequestRepository repository;


	@Override
	public boolean authorise(final Request<BookKeeperRequest> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<BookKeeperRequest> request, final BookKeeperRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firmName", "responsibilityStatement", "status");
	}

	@Override
	public BookKeeperRequest findOne(final Request<BookKeeperRequest> request) {
		assert request != null;

		int id = request.getPrincipal().getAccountId();
		BookKeeperRequest bookKeeperRequest = this.repository.findBookKeeperRequestByUserId(id);

		return bookKeeperRequest;
	}
}
