
package acme.features.anonymous.donaireBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.DonaireBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/donaire-bulletin/")
public class AnonymousDonaireBulletinController extends AbstractController<Anonymous, DonaireBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousDonaireBulletinListService		listService;
	@Autowired
	private AnonymousDonaireBulletinCreateService	createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
