
package acme.features.administrator.bookKeeperRequest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bookKeeperRequest.BookKeeperRequest;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorBookKeeperRequestListService implements AbstractListService<Administrator, BookKeeperRequest> {

	@Autowired
	AdministratorBookKeeperRequestRepository repository;


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

		request.unbind(entity, model, "firmName", "userAccount.username");
	}

	@Override
	public Collection<BookKeeperRequest> findMany(final Request<BookKeeperRequest> request) {
		assert request != null;

		Collection<BookKeeperRequest> result = this.repository.findMany();

		return result;
	}

}
