
package acme.features.authenticated.investmentRounds;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRounds.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInvestmentRoundRepository extends AbstractRepository {

	@Query("select ir from InvestmentRound ir")
	Collection<InvestmentRound> findActivesInvestmentRounds();

	@Query("select ir from InvestmentRound ir where ir.id =?1")
	InvestmentRound findOneInvestmentRoundById(int id);
}
