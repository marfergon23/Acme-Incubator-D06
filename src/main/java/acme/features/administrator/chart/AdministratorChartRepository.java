
package acme.features.administrator.chart;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorChartRepository extends AbstractRepository {

	@Query("select c.activitySector,count(c) FROM Technology c group by c.activitySector order by c.activitySector")
	Object[] findTechnologiesSector();


	@Query("select c.sector,count(c) FROM ToolRecord c group by c.sector order by c.sector")
	Object[] findToolsSector();


	@Query("select j.indication,count(j) FROM Technology j group by j.indication order by j.indication")
	Object[] findTechnologiesStatus();


	@Query("select a.indication,count(a) FROM ToolRecord a group by a.indication order by a.indication")
	Object[] findToolsStatus();
	

	@Query("select ir.kindRound,count(ir) FROM InvestmentRound ir group by ir.kindRound order by ir.kindRound")
	Object[] findInvestmentRoundKindRound();

	@Query("select a.statement,count(a) FROM Application a group by a.statement order by a.statement")
	Object[] findApplicationStatement();
	
	@Query("select date(a.moment),count(a) FROM Application a where a.moment > ?1 and a.statement = 0 group by day(a.moment)")
	Object[] findRejectedApplicationsLastThreeWeeks(Date d);

	@Query("select date(a.moment),count(a) FROM Application a where a.moment > ?1 and a.statement = 1 group by day(a.moment)")
	Object[] findPendingApplicationsLastThreeWeeks(Date d);

	@Query("select date(a.moment),count(a) FROM Application a where a.moment > ?1 and a.statement = 2 group by day(a.moment)")
	Object[] findAcceptedApplicationsLastThreeWeeks(Date d);


}
