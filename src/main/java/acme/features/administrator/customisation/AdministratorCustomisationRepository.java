
package acme.features.administrator.customisation;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisations.Customisation;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCustomisationRepository extends AbstractRepository {

	@Query("Select c from Customisation c")
	Collection<Customisation> findMany();

	@Query("select c from Customisation c where c.id = ?1")
	Customisation findOneById(int id);
}
