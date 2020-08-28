
package acme.features.administrator.inquirie;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inquiries.Inquirie;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorInquirieRepository extends AbstractRepository {

	@Query("select i from Inquirie i")
	Collection<Inquirie> findManyInquiries();

	@Query("select i from Inquirie i where i.id =?1")
	Inquirie findOneInquireById(int id);
}
