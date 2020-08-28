
package acme.features.bookkeeper.investment;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/bookkeeper/investment-round/")
public class BookkeeperInvestmentController extends AbstractController<Bookkeeper, InvestmentRound> {

	@Autowired
	private BookkeeperInvestmentListMineService		listMineService;

	@Autowired
	private BookkeeperInvestmentListNotMineService	listNotMineService;

	@Autowired
	private BookkeeperInvestmentShowService			showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addCustomCommand(CustomCommand.LIST_NOT_MINE, BasicCommand.LIST, this.listNotMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

	}
}
