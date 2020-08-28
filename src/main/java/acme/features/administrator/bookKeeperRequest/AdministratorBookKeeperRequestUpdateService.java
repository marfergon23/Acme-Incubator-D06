
package acme.features.administrator.bookKeeperRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bookKeeperRequest.BookKeeperRequest;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorBookKeeperRequestUpdateService implements AbstractUpdateService<Administrator, BookKeeperRequest> {

	@Autowired
	AdministratorBookKeeperRequestRepository repository;


	@Override
	public boolean authorise(final Request<BookKeeperRequest> request) {
		assert request != null;

		return true;
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

		request.unbind(entity, model, "firmName", "responsibilityStatement", "status");
	}

	@Override
	public BookKeeperRequest findOne(final Request<BookKeeperRequest> request) {
		assert request != null;

		BookKeeperRequest result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findBookKeeperRequestById(id);

		return result;
	}

	@Override
	public void validate(final Request<BookKeeperRequest> request, final BookKeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<BookKeeperRequest> request, final BookKeeperRequest entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

		if (entity.getStatus()) {
			Bookkeeper bookKeeper = new Bookkeeper();
			bookKeeper.setFirmName(entity.getFirmName());
			bookKeeper.setResponsibilityStatement(entity.getResponsibilityStatement());
			bookKeeper.setUserAccount(entity.getUserAccount());
			this.repository.save(bookKeeper);
		}
	}

}
