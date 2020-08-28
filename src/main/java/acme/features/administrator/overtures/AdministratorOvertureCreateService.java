
package acme.features.administrator.overtures;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overtures.Overture;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorOvertureCreateService implements AbstractCreateService<Administrator, Overture> {

	// Internal state ------------------------------------------------------------------
	@Autowired
	AdministratorOvertureRepository repository;


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "email", "minMoney", "maxMoney");
	}

	@Override
	public Overture instantiate(final Request<Overture> request) {
		Overture result = new Overture();
		Date moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);
		return result;
	}

	@Override
	public void validate(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isDeadlineFuture, isMinMoneyEuro, isMaxMoneyEuro, isMinMoneyLowerThanMaxMoney;

		if (!errors.hasErrors("deadline")) {
			Calendar calendar = new GregorianCalendar();
			Date currentMoment = calendar.getTime();
			isDeadlineFuture = request.getModel().getDate("deadline").after(currentMoment);
			errors.state(request, isDeadlineFuture, "deadline", "administrator.overture.error.deadline");
		}

		if (!errors.hasErrors("minMoney")) {
			String minMoney = entity.getMinMoney().getCurrency();
			isMinMoneyEuro = minMoney.equals("€") || minMoney.equals("EUR");
			errors.state(request, isMinMoneyEuro, "minMoney", "administrator.overture.error.minMoney");
		}

		if (!errors.hasErrors("maxMoney")) {
			String maxMoney = entity.getMaxMoney().getCurrency();
			isMaxMoneyEuro = maxMoney.equals("€") || maxMoney.equals("EUR");
			errors.state(request, isMaxMoneyEuro, "maxMoney", "administrator.overture.error.maxMoney");
		}

		if (!errors.hasErrors("minMoney") && !errors.hasErrors("maxMoney")) {
			Double minMoney = entity.getMinMoney().getAmount();
			Double maxMoney = entity.getMaxMoney().getAmount();
			isMinMoneyLowerThanMaxMoney = minMoney < maxMoney;
			errors.state(request, isMinMoneyLowerThanMaxMoney, "minMoney", "administrator.overture.error.minMoneyLower");
		}
	}

	@Override
	public void create(final Request<Overture> request, final Overture entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);
	}
}
