
package acme.features.administrator.challenge;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "deadline", "description", "rookieGoal", "rookieReward", "averageGoal", "averageReward", "expertGoal", "expertReward");
	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {

		this.repository.save(entity);

	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		Challenge result;

		result = new Challenge();
		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Comprobamos que el deadline es fecha futura
		boolean isDeadlineFuture, isRookieRewardEuro, isAverageRewardEuro, isExpertRewardEuro, rookieAmount, averageAmount;

		if (!errors.hasErrors("deadline")) {
			Calendar calendar = new GregorianCalendar();
			Date currentMoment = calendar.getTime();
			isDeadlineFuture = request.getModel().getDate("deadline").after(currentMoment);
			errors.state(request, isDeadlineFuture, "deadline", "administrator.challenge.error.deadline");
		}

		//Comprobamos que solo se puede meter Euros
		if (!errors.hasErrors("rookieReward")) {
			String rookieReward = entity.getRookieReward().getCurrency();
			isRookieRewardEuro = rookieReward.equals("€") || rookieReward.equals("EUR");
			errors.state(request, isRookieRewardEuro, "rookieReward", "administrator.challenge.error.rookieReward");
		}

		if (!errors.hasErrors("averageReward")) {
			String averageReward = entity.getAverageReward().getCurrency();
			isAverageRewardEuro = averageReward.equals("€") || averageReward.equals("EUR");
			errors.state(request, isAverageRewardEuro, "averageReward", "administrator.challenge.error.averageReward");
		}

		if (!errors.hasErrors("expertReward")) {
			String expertReward = entity.getExpertReward().getCurrency();
			isExpertRewardEuro = expertReward.equals("€") || expertReward.equals("EUR");
			errors.state(request, isExpertRewardEuro, "expertReward", "administrator.challenge.error.expertReward");
		}

		//Comprobamos que rookie sea mayor que average
		if (!errors.hasErrors("rookieReward") && !errors.hasErrors("averageReward")) {

			Double averageReward = entity.getAverageReward().getAmount();
			Double rookieReward = entity.getRookieReward().getAmount();
			rookieAmount = averageReward > rookieReward;
			errors.state(request, rookieAmount, "rookieReward", "administrator.challenge.error.minRookieReward");
		}

		//Comprobamos que average sea mayor que expert
		if (!errors.hasErrors("averageReward") && !errors.hasErrors("rookieReward") && !errors.hasErrors("expertReward")) {

			Double expertReward = entity.getExpertReward().getAmount();
			Double averageReward = entity.getAverageReward().getAmount();
			Double rookieReward = entity.getRookieReward().getAmount();
			averageAmount = expertReward > averageReward && expertReward > rookieReward;
			errors.state(request, averageAmount, "averageReward", "administrator.challenge.error.minAverageReward");
		}

	}

}
