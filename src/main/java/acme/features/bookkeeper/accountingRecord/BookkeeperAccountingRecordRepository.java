
package acme.features.bookkeeper.accountingRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperAccountingRecordRepository extends AbstractRepository {

	@Query("select ar from AccountingRecord ar where ar.id = ?1")
	AccountingRecord findOneById(int id);

	@Query("select ar from AccountingRecord ar")
	Collection<AccountingRecord> findManyAccountingRecord();

	@Query("select b from Bookkeeper b where b.id = ?1")
	Bookkeeper findBookkeeperById(int id);
	
	@Query("select i from InvestmentRound i where i.id = ?1")
	InvestmentRound findOneInvestmentRoundById(int id);

	//@Query("select ar from AccountingRecord ar where ar.investmentRound.id = ?1 and (ar.finalMode is true or ar.bookkeeper.id = ?2)")
	//Collection<AccountingRecord> findManyByBookkeeperIdAndInvestmentRoundId(int bookkeeperId, int investmentRoundId);

	@Query("select ar from AccountingRecord ar where ar.investmentRound.id = ?1 and ar.finalMode = true")
	Collection<AccountingRecord> findManyFinalByInvesmentRoundId(int investmentRoundId);

	

}
