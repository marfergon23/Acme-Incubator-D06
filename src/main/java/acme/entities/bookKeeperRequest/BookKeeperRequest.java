
package acme.entities.bookKeeperRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BookKeeperRequest extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				firmName;

	@NotBlank
	@Column(length = 3000)
	private String				responsibilityStatement;

	private Boolean				status;

	@Valid
	@NotNull
	@OneToOne(optional = false)
	private UserAccount			userAccount;
}
