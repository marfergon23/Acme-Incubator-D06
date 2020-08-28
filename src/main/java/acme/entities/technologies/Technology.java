
package acme.entities.technologies;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Technology extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@NotBlank
	private String				activitySector;

	@NotBlank
	private String				inventorName;

	@NotBlank
	private String				description;

	@URL
	@NotBlank
	private String				webSite;

	@Email
	@NotBlank
	private String				email;

	@NotBlank
	@Pattern(regexp = "^(open-source|closed-source)$")
	private String				indication;

	@Range(min = -5, max = 5)
	private Integer				stars;

}
