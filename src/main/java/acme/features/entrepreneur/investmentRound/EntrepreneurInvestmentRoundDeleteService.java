
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.applications.Application;
import acme.entities.forums.Forum;
// import acme.entities.accountingRecords.AccountingRecord;
// import acme.entities.forums.Forum;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.messages.Message;
import acme.entities.roles.Entrepreneur;
import acme.entities.workProgrammes.WorkProgramme;
import acme.features.authenticated.forum.AuthenticatedForumRepository;
import acme.features.entrepreneur.application.EntrepreneurApplicationRepository;
import acme.features.entrepreneur.workProgramme.EntrepreneurWorkProgrammeRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EntrepreneurInvestmentRoundDeleteService implements AbstractDeleteService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository	repository;

	@Autowired
	EntrepreneurWorkProgrammeRepository		workProgrammeRepository;

	@Autowired
	EntrepreneurApplicationRepository		applicationRepository;

	@Autowired
	AuthenticatedForumRepository			forumRepository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "moment", "kindRound", "title", "description", "amountMoney", "link", "finalMode");
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;
		InvestmentRound result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneInvestmentRoundById(id);
		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int investmentRoundId;
		int numApplications;

		// Borrado incorrecto
		if (!errors.hasErrors("ticker")) {

			investmentRoundId = request.getModel().getInteger("id");
			numApplications = this.repository.findApplicationsByInvestmentRoundId(investmentRoundId);
			errors.state(request, numApplications == 0, "ticker", "entrepreneur.investment-round.form.error.tieneSolucitudes");
		}
	}

	@Override
	public void delete(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		Collection<WorkProgramme> workProgrammes = this.repository.findWorkProgrammesByInvestmentRoundId(entity.getId());
		if (!workProgrammes.isEmpty()) {
			for (WorkProgramme wp : workProgrammes) {
				this.workProgrammeRepository.delete(wp);
			}
		}

		Collection<Message> messages = this.repository.findMessagesByInvestmentRoundId(entity.getId());
		if (!messages.isEmpty()) {
			for (Message m : messages) {
				this.repository.delete(m);
			}
		}

		Optional<Forum> forum = this.repository.findForumByInvestmentRoundId(entity.getId());
		if (forum.isPresent()) {
			Forum forumExist = forum.get();
			this.forumRepository.delete(forumExist);
		}

		Collection<AccountingRecord> accountingRecords = this.repository.findAccountingRecordsByInvestmentRoundId(entity.getId());
		if (!accountingRecords.isEmpty()) {
			for (AccountingRecord ar : accountingRecords) {
				// Puede que tengas que llamar al repositorio de accounting record cuando
				// se mergee que aun no se ha mergeado porque no sabemos si Sara ha terminado
				this.workProgrammeRepository.delete(ar);
			}
		}

		Collection<Application> applications = this.repository.findApplicationByInvestmentRoundId(entity.getId());
		if (!applications.isEmpty()) {
			for (Application a : applications) {
				this.applicationRepository.delete(a);
			}
		}

		this.repository.delete(entity);
	}

}
