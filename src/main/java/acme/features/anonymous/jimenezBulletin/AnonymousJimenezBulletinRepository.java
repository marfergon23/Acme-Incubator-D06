
package acme.features.anonymous.jimenezBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.JimenezBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousJimenezBulletinRepository extends AbstractRepository {

	@Query("Select jb from JimenezBulletin jb")
	Collection<JimenezBulletin> findMany();
}
