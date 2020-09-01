
package acme.features.patron.creditCard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Banner;
import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronCreditCardRepository extends AbstractRepository {

	@Query("select p from Patron p where p.id = ?1")
	Patron findOnePatronByUserAccountId(int id);

	@Query("select b from Banner b where b.id = ?1")
	Banner findBannerById(int bannerId);

	@Query("select cd from CreditCard cd where cd.id =?1")
	CreditCard findOneCreditCardById(int id);

	@Query("select b from Banner b where b.creditCard.id = ?1")
	Banner findBannerByCreditCardId(int creditCardId);
}
