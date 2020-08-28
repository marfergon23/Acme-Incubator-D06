
package acme.features.administrator.inquirie;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquiries.Inquirie;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorInquirieUpdateService implements AbstractUpdateService<Administrator, Inquirie> {

	// Internal state ------------------------------------------------------------------

	@Autowired
	AdministratorInquirieRepository repository;


	@Override
	public boolean authorise(final Request<Inquirie> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Inquirie> request, final Inquirie entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Inquirie> request, final Inquirie entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationDate", "endDate", "description", "minMoney", "maxMoney", "email");

	}

	@Override
	public Inquirie findOne(final Request<Inquirie> request) {
		assert request != null;

		Inquirie result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneInquireById(id);
		return result;
	}

	@Override
	public void validate(final Request<Inquirie> request, final Inquirie entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isEndDateFuture, isMinMoneyEuro, isMaxMoneyEuro, isMinMoneyLowerThanMaxMoney;

		if (!errors.hasErrors("endDate")) {
			Calendar calendar = new GregorianCalendar();
			Date currentMoment = calendar.getTime();
			isEndDateFuture = request.getModel().getDate("endDate").after(currentMoment);
			errors.state(request, isEndDateFuture, "endDate", "administrator.inquirie.error.endDate");
		}

		if (!errors.hasErrors("minMoney")) {
			String minMoney = entity.getMinMoney().getCurrency();
			isMinMoneyEuro = minMoney.equals("€") || minMoney.equals("EUR");
			errors.state(request, isMinMoneyEuro, "minMoney", "administrator.inquirie.error.minMoney");
		}

		if (!errors.hasErrors("maxMoney")) {
			String maxMoney = entity.getMaxMoney().getCurrency();
			isMaxMoneyEuro = maxMoney.equals("€") || maxMoney.equals("EUR");
			errors.state(request, isMaxMoneyEuro, "maxMoney", "administrator.inquirie.error.maxMoney");
		}

		if (!errors.hasErrors("minMoney") && !errors.hasErrors("maxMoney")) {
			Double minMoney = entity.getMinMoney().getAmount();
			Double maxMoney = entity.getMaxMoney().getAmount();
			isMinMoneyLowerThanMaxMoney = minMoney < maxMoney;
			errors.state(request, isMinMoneyLowerThanMaxMoney, "minMoney", "administrator.inquirie.error.minMoneyLower");
		}
	}

	@Override
	public void update(final Request<Inquirie> request, final Inquirie entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
