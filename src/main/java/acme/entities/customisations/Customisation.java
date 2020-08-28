
package acme.entities.customisations;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customisation extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Pattern(regexp = "^$|(^[^,]+([,][^,]+)*$)")
	private String				spamwords;

	@Range(min = 0, max = 100)
	@NotNull
	private Double				threshold;

	@NotBlank
	private String				activitySectors;


	public boolean isSpam(final String text) {
		String lowerCaseText = text.toLowerCase();

		int spamCount = 0;
		for (String spamWord : this.spamwords.toLowerCase().split(",")) {
			spamCount += StringUtils.countMatches(lowerCaseText, spamWord) * spamWord.length();
		}

		return (float) spamCount / text.length() * 100 > this.threshold;
	}
}
