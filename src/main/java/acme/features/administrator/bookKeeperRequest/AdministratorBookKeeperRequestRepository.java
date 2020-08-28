
package acme.features.administrator.bookKeeperRequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bookKeeperRequest.BookKeeperRequest;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorBookKeeperRequestRepository extends AbstractRepository {

	@Query("select bkr from BookKeeperRequest bkr where bkr.status=null ")
	Collection<BookKeeperRequest> findMany();

	@Query("select bkr from BookKeeperRequest bkr where bkr.id = ?1")
	BookKeeperRequest findBookKeeperRequestById(int id);
}
