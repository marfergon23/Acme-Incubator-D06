
package acme.features.authenticated.forum;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.forums.Forum;
import acme.entities.investmentRounds.InvestmentRound;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedForumCreateService implements AbstractCreateService<Authenticated, Forum> {

	@Autowired
	private AuthenticatedForumRepository repository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;
		Boolean result = false;
		int investmentRoundId = request.getModel().getInteger("investmentRoundId");
		int cantidadForums = this.repository.findForumsByInvestmentRoundId(investmentRoundId);
		if (cantidadForums == 0) {
			result = true;
		}
		return result;
	}

	@Override
	public void bind(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("investmentRoundId", entity.getInvestmentRound().getId());

		request.unbind(entity, model, "title");
	}

	@Override
	public Forum instantiate(final Request<Forum> request) {
		assert request != null;

		Forum result = new Forum();
		Principal principal;
		Authenticated user;
		String username;
		int investmentRoundId;
		InvestmentRound investmentRound;

		principal = request.getPrincipal();
		user = this.repository.findOneAuthenticatedById(principal.getActiveRoleId());
		username = user.getUserAccount().getUsername();

		investmentRoundId = request.getModel().getInteger("investmentRoundId");
		investmentRound = this.repository.findOneInvestmentRoundById(investmentRoundId);

		result.setUsers(username);
		result.setInvestmentRound(investmentRound);

		return result;
	}

	@Override
	public void validate(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean spamTitle = false;
		Customisation customisation = this.repository.findCustomisation();

		if (!errors.hasErrors("title")) {
			Integer contador = 0;
			Double threshold = customisation.getThreshold();
			List<String> spamWords = Arrays.asList(customisation.getSpamwords().split(","));
			String title = entity.getTitle().toLowerCase();
			for (String spamword : spamWords) {
				Integer posicion = title.indexOf(spamword);
				while (posicion != -1) {
					contador++;
					title = title.substring(posicion + 1);
					posicion = title.indexOf(spamword);
				}
			}
			List<String> listTitle = Arrays.asList(title.split(" "));
			Integer totalnumberTitle = listTitle.size();
			Double division = (double) (contador / totalnumberTitle) * 100;
			if (threshold > division) {
				spamTitle = true;
			}
			errors.state(request, spamTitle, "title", "authenticated.forum.form.error.spamword-title");
		}
	}

	@Override
	public void create(final Request<Forum> request, final Forum entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
