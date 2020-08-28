
package acme.features.entrepreneur.workProgramme;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workProgrammes.WorkProgramme;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurWorkProgrammeRepository extends AbstractRepository {

	@Query("select wp from WorkProgramme wp where wp.investmentRound.id =?1")
	Collection<WorkProgramme> findAllWorkProgrammeById(int id);

	@Query("select wp from WorkProgramme wp where wp.id =?1")
	WorkProgramme findOneWorkProgrammeById(int id);

	@Query("select wp.budget.amount from WorkProgramme wp where wp.id =?1")
	int findOneBudgetByWorkProgrammeId(int id);
}
