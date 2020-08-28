
package acme.features.authenticated.inquirie;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inquiries.Inquirie;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInquirieRepository extends AbstractRepository {

	@Query("select i from Inquirie i where i.endDate > CURRENT_TIMESTAMP")
	Collection<Inquirie> findManyInquiries();

	@Query("select i from Inquirie i where i.id =?1")
	Inquirie findOneInquireById(int id);
}
