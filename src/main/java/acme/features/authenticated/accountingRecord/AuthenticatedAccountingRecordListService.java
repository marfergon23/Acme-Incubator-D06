package acme.features.authenticated.accountingRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;


import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAccountingRecordListService implements AbstractListService<Authenticated, AccountingRecord>{

	@Autowired
	private AuthenticatedAccountingRecordRepository repository;

	
	@Override
	public boolean authorise(Request<AccountingRecord> request) {
		assert request != null;

		return true;
	
	}

	@Override
	public void unbind(Request<AccountingRecord> request, AccountingRecord entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "finalMode", "body", "status");
		
	}

	@Override
	public Collection<AccountingRecord> findMany(Request<AccountingRecord> request) {
		assert request != null;

		Collection<AccountingRecord> result;
		
		int investmentId;
		investmentId = request.getModel().getInteger("investmentId");

		result = this.repository.findManyFinalByInvesmentRoundId(investmentId);
		
		
		return result;
	}
	

	

}
