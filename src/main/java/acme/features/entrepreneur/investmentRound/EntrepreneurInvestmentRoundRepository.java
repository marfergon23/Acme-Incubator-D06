
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.applications.Application;
import acme.entities.customisations.Customisation;
import acme.entities.forums.Forum;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.messages.Message;
import acme.entities.roles.Entrepreneur;
import acme.entities.workProgrammes.WorkProgramme;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentRoundRepository extends AbstractRepository {

	@Query("select ir from InvestmentRound ir where ir.entrepreneur.id =?1")
	Collection<InvestmentRound> findActivesInvestmentRounds(int entrepeneurId);

	@Query("select ir from InvestmentRound ir where ir.id =?1")
	InvestmentRound findOneInvestmentRoundById(int id);

	@Query("select e from Entrepreneur e where e.id = ?1")
	Entrepreneur findOneEntrepreneurById(int id);

	@Query("select wp from WorkProgramme wp where wp.investmentRound.id = ?1")
	Collection<WorkProgramme> findWorkProgrammesByInvestmentRoundId(int investmentRoundId);

	@Query("select f from Forum f where f.investmentRound.id = ?1")
	Optional<Forum> findForumByInvestmentRoundId(int investmentRoundId);

	@Query("select ar from AccountingRecord ar where ar.investmentRound.id = ?1")
	Collection<AccountingRecord> findAccountingRecordsByInvestmentRoundId(int investmentRoundId);

	@Query("select a from Application a where a.investmentRound.id = ?1")
	Collection<Application> findApplicationByInvestmentRoundId(int investmentRoundId);

	@Query("select ir.ticker from InvestmentRound ir")
	Collection<String> findAllTickers();

	@Query("select c from Customisation c")
	Customisation findCustomisation();

	@Query("select m from Message m where m.forum.investmentRound.id = ?1")
	Collection<Message> findMessagesByInvestmentRoundId(int id);

	@Query("select count(a) from Application a where a.investmentRound.id = ?1")
	int findApplicationsByInvestmentRoundId(int investmentRoundId);

	@Query("select sum(wp.budget.amount) from WorkProgramme wp where wp.investmentRound.id = ?1")
	Double sumBudgetWorkProgramme(int id);

	@Query("select count(wp) from WorkProgramme wp where wp.investmentRound.id = ?1")
	int countWorkProgrammesByInvestmentRoundId(int investmentRoundId);

	@Query("select ir.ticker from InvestmentRound ir where ir.id = ?1")
	String findOneTickerById(int id);

}
