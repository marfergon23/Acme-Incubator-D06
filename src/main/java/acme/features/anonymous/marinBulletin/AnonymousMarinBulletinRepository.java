package acme.features.anonymous.marinBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.MarinBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousMarinBulletinRepository extends AbstractRepository {

	@Query("select m from MarinBulletin m")
	Collection<MarinBulletin> findMany();
}