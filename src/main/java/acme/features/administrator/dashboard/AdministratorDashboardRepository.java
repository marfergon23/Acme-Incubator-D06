
package acme.features.administrator.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inquiries.Inquirie;
import acme.entities.overtures.Overture;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(n) from Notice n")
	Integer numberNotices();

	@Query("select count(t) from Technology t")
	Integer numberTechnologies();

	@Query("select count(r) from ToolRecord r")
	Integer numberTools();

	@Query("select min (minMoney.amount) from Inquirie where endDate>CURRENT_TIMESTAMP")
	Double minMoneyActiveInquiries();

	@Query("select max (maxMoney.amount) from Inquirie where endDate>CURRENT_TIMESTAMP")
	Double maxMoneyActiveInquiries();

	@Query("select avg ((max_money_amount + min_money_amount)/2) from Inquirie where endDate>CURRENT_TIMESTAMP")
	Double avgMoneyActiveInquiries();

	@Query("select o from Inquirie o where endDate>CURRENT_TIMESTAMP")
	Collection<Inquirie> stddevMoneyActiveInquiries();

	@Query("select min (minMoney.amount) from Overture where deadline>CURRENT_TIMESTAMP")
	Double minMoneyActiveOvertures();

	@Query("select max (maxMoney.amount) from Overture where deadline>CURRENT_TIMESTAMP")
	Double maxMoneyActiveOvertures();

	@Query("select avg ((max_money_amount + min_money_amount)/2) from Overture where deadline>CURRENT_TIMESTAMP")
	Double avgMoneyActiveOvertures();

	@Query("select o from Overture o where deadline>CURRENT_TIMESTAMP")
	Collection<Overture> stddevMoneyActiveOvertures();

	//D04

	@Query("select count(e) from Entrepreneur e")
	Double numberEntrepreneurs();

	@Query("select count(ir) from InvestmentRound ir")
	Double numberInvestmentRounds();

	@Query("select count(a) from Application a")
	Double numberApplications();

	@Query("select count(i) from Investor i")
	Double numberInvestors();
}
