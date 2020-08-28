
package acme.features.authenticated.challenge;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.challenges.Challenge;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedChallengeRepository extends AbstractRepository {

	@Query("Select c from Challenge c where c.deadline > CURRENT_TIMESTAMP")
	Collection<Challenge> findMany();

	@Query("Select c from Challenge c where c.id = ?1")
	Challenge findOne(int id);
}
