
package acme.features.entrepreneur.workProgramme;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Entrepreneur;
import acme.entities.workProgrammes.WorkProgramme;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneurWorkProgrammeListService implements AbstractListService<Entrepreneur, WorkProgramme> {

	@Autowired
	EntrepreneurWorkProgrammeRepository repository;


	@Override
	public boolean authorise(final Request<WorkProgramme> request) {
		assert request != null;
		boolean result = true;
		int investId;
		//WorkProgramme wp;
		Entrepreneur entrepreneur;
		Principal principal;

		investId = request.getModel().getInteger("id");
		Optional<WorkProgramme> wpr;
		wpr = this.repository.findAllWorkProgrammeById(investId).stream().findAny();
		if (wpr.isPresent()) {
			entrepreneur = wpr.get().getInvestmentRound().getEntrepreneur();
			principal = request.getPrincipal();
			result = entrepreneur.getUserAccount().getId() == principal.getAccountId();
		}

		return result;
	}

	@Override
	public void unbind(final Request<WorkProgramme> request, final WorkProgramme entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment");
	}

	@Override
	public Collection<WorkProgramme> findMany(final Request<WorkProgramme> request) {
		assert request != null;

		Collection<WorkProgramme> res;

		res = this.repository.findAllWorkProgrammeById(request.getModel().getInteger("id"));
		return res;
	}

}
