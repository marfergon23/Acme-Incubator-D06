
package acme.features.authenticated.accountingRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import acme.entities.accountingRecords.AccountingRecord;

import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/accounting-record/")
public class AuthenticatedAccountingRecordController extends AbstractController<Authenticated,AccountingRecord > {

	@Autowired
	private AuthenticatedAccountingRecordListService		listService;
	
	@Autowired
	private AuthenticatedAccountingRecordShowService			showService;
	


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		


	}
}
