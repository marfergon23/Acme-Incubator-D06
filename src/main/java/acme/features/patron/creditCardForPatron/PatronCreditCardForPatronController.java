
package acme.features.patron.creditCardForPatron;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.creditCards.CreditCardForPatron;
import acme.entities.roles.Patron;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/patron/credit-card-for-patron/")
public class PatronCreditCardForPatronController extends AbstractController<Patron, CreditCardForPatron> {

	@Autowired
	private PatronCreditCardForPatronCreateService	createService;

	@Autowired
	private PatronCreditCardForPatronShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
