
package acme.features.anonymous.jimenezBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.JimenezBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/jimenez-bulletin/")
public class AnonymousJimenezBulletinController extends AbstractController<Anonymous, JimenezBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousJimenezBulletinListService		listService;
	@Autowired
	private AnonymousJimenezBulletinCreateService	createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
