
package acme.features.patron.creditCardForPatron;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.creditCards.CreditCardForPatron;
import acme.entities.roles.Patron;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronCreditCardForPatronRepository extends AbstractRepository {

	@Query("select p from Patron p where p.id = ?1")
	Patron findOnePatronByUserAccountId(int id);

	@Query("select cd from CreditCardForPatron cd where cd.id =?1")
	CreditCardForPatron findOneCreditCardForPatronById(int id);

}
