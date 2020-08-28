
package acme.features.anonymous.technology;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.technologies.Technology;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousTechnologyRepository extends AbstractRepository {

	@Query("select t from Technology t where t.id = ?1")
	Technology findOneById(int id);

	@Query("select t from Technology t")
	Collection<Technology> findManyAll();

}
