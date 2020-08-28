
package acme.features.administrator.technology;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.technologies.Technology;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/technology/")
public class AdministratorTechnologyController extends AbstractController<Administrator, Technology> {

	@Autowired
	private AdministratorTechnologyListService		listService;

	@Autowired
	private AdministratorTechnologyShowService		showService;

	@Autowired
	private AdministratorTechnologyCreateService	createService;

	@Autowired
	private AdministratorTechnologyUpdateService	updateService;

	@Autowired
	private AdministratorTechnologyDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
