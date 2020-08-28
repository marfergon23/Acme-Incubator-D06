
package acme.features.entrepreneur.workProgramme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.entities.workProgrammes.WorkProgramme;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurWorkProgrammeShowService implements AbstractShowService<Entrepreneur, WorkProgramme> {

	@Autowired
	EntrepreneurWorkProgrammeRepository repository;


	@Override
	public boolean authorise(final Request<WorkProgramme> request) {
		assert request != null;
		boolean result;
		int wpId;
		InvestmentRound ir;
		Entrepreneur entrepreneur;
		Principal principal;

		wpId = request.getModel().getInteger("id");
		ir = this.repository.findOneWorkProgrammeById(wpId).getInvestmentRound();
		entrepreneur = ir.getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<WorkProgramme> request, final WorkProgramme entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "deadline", "budget");

		model.setAttribute("investmentRoundId", entity.getInvestmentRound().getId());
		model.setAttribute("finalMode", entity.getInvestmentRound().getFinalMode());
	}

	@Override
	public WorkProgramme findOne(final Request<WorkProgramme> request) {
		assert request != null;

		WorkProgramme res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.repository.findOneWorkProgrammeById(id);
		return res;
	}

}
