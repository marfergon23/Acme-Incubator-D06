
package acme.features.anonymous.donaireBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.DonaireBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousDonaireBulletinRepository extends AbstractRepository {

	@Query("Select db from DonaireBulletin db")
	Collection<DonaireBulletin> findMany();
}
