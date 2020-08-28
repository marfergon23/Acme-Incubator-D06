
package acme.features.administrator.bookKeeperRequest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bookKeeperRequest.BookKeeperRequest;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/book-keeper-request/")
public class AdministratorBookKeeperRequestController extends AbstractController<Administrator, BookKeeperRequest> {

	@Autowired
	private AdministratorBookKeeperRequestUpdateService	updateService;
	@Autowired
	private AdministratorBookKeeperRequestListService	listService;
	@Autowired
	private AdministratorBookKeeperRequestShowService	showService;

	// Constructors --------------------------------------------------


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
