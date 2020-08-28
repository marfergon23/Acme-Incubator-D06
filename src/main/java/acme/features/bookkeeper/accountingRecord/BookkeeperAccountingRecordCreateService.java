package acme.features.bookkeeper.accountingRecord;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;

import acme.framework.services.AbstractCreateService;

import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;

@Service
public class BookkeeperAccountingRecordCreateService implements AbstractCreateService<Bookkeeper, AccountingRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private BookkeeperAccountingRecordRepository repository;
	
	
	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment", "invesmentRound", "bookkeeper", "status", "finalMode");
	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body");

	}

	@Override
	public AccountingRecord instantiate(final Request<AccountingRecord> request) {
		
		AccountingRecord result;
		result = new AccountingRecord();
		
		InvestmentRound ir;
		Bookkeeper b;
		b = this.repository.findBookkeeperById(request.getPrincipal().getActiveRoleId());
		ir = this.repository.findOneInvestmentRoundById(request.getModel().getInteger("investmentId"));
		System.out.println(b);
		result.setBookkeeper(b);
		result.setInvestmentRound(ir);
		result.setStatus("draft");
		result.setFinalMode(false); 
		return result; 
	}

	@Override
	public void validate(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AccountingRecord> request, final AccountingRecord entity) {
		Date creationMoment;

		creationMoment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(creationMoment);
		entity.setStatus("draft");
		entity.setFinalMode(false);
		this.repository.save(entity);

	}
}
