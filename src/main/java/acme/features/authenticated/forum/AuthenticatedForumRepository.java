
package acme.features.authenticated.forum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisations.Customisation;
import acme.entities.forums.Forum;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.messages.Message;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedForumRepository extends AbstractRepository {

	@Query("select f from Forum f")
	Collection<Forum> findMany();

	@Query("select f from Forum f where f.id = ?1")
	Forum findOneById(int id);

	@Query("select au from Authenticated au where au.id = ?1")
	Authenticated findOneAuthenticatedById(int id);

	@Query("select ir from InvestmentRound ir where ir.id = ?1")
	InvestmentRound findOneInvestmentRoundById(int id);

	@Query("select u from UserAccount u")
	Collection<UserAccount> findAllUserAccounts();

	@Query("select m from Message m where m.forum.id = ?1")
	Collection<Message> findManyMessagesByForumId(int id);

	@Query("select c from Customisation c")
	Customisation findCustomisation();

	@Query("select count(f) from Forum f where f.investmentRound.id = ?1")
	int findForumsByInvestmentRoundId(int id);

}
