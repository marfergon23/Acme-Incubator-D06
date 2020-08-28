
package acme.features.entrepreneur.accountingRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import acme.entities.accountingRecords.AccountingRecord;


import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/accounting-record/")
public class EntrepreneurAccountingRecordController extends AbstractController<Entrepreneur, AccountingRecord > {

	@Autowired
	private EntrepreneurAccountingRecordListService		listService;
	
	@Autowired
	private EntrepreneurAccountingRecordShowService			showService;
	


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		


	}
}
