
package acme.features.anonymous.gonzalezBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.GonzalezBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousGonzalezBulletinRepository extends AbstractRepository {

	@Query("Select gb from GonzalezBulletin gb")
	Collection<GonzalezBulletin> findMany();

}
