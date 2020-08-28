
package acme.features.authenticated.bookKeeperRequest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bookKeeperRequest.BookKeeperRequest;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBookKeeperRequestRepository extends AbstractRepository {

	@Query("select bkr from BookKeeperRequest bkr where bkr.userAccount.id=?1 ")
	BookKeeperRequest findBookKeeperRequestByUserId(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findUserAccountById(int id);
}
