
package acme.features.authenticated.bookKeeperRequest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bookKeeperRequest.BookKeeperRequest;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/book-keeper-request/")
public class AuthenticatedBookKeeperRequestController extends AbstractController<Authenticated, BookKeeperRequest> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedBookKeeperRequestCreateService	createService;

	@Autowired
	private AuthenticatedBookKeeperRequestShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
