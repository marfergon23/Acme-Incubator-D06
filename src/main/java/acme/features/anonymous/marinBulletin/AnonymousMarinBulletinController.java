package acme.features.anonymous.marinBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.MarinBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/marin-bulletin/")
public class AnonymousMarinBulletinController extends AbstractController<Anonymous, MarinBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousMarinBulletinListService		listService;
	@Autowired
	private AnonymousMarinBulletinCreateService	createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}

