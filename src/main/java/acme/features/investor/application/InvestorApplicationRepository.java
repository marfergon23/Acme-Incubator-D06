
package acme.features.investor.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.roles.Investor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOneApplicationById(int id);

	@Query("select a from Application a where a.investor.id = ?1")
	Collection<Application> findManyByInvestorId(int investorId);

	@Query("select i from Investor i where i.id = ?1")
	Investor findOneInvestorById(int id);

	@Query("select a from Application a where a.investor.id = ?1 and a.investmentRound.id = ?2")
	Application findOneApplicationByInvestorIdAndInvestmentRoundId(int invId, int irId);

	@Query("select a.ticker from Application a")
	Collection<String> findAllApplicationTickers();

	@Query("select count(a) from Application a where a.investmentRound.id = ?1 and a.investor.id = ?2")
	int findApplicationsByInvestmentRoundId(int irId, int invId);
}
