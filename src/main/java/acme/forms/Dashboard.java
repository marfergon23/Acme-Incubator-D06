
package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	Integer						numberNotices;

	Integer						numberTechnologies;

	Integer						numberTools;

	Double						minMoneyActiveInquiries;

	Double						maxMoneyActiveInquiries;

	Double						averageMoneyActiveInquiries;

	Double						stddevMoneyActiveInquiries;

	Double						minMoneyActiveOvertures;

	Double						maxMoneyActiveOvertures;

	Double						averageMoneyActiveOvertures;

	Double						stddevMoneyActiveOvertures;

	Double						averageInvestmentRoundsPerEntrepreneur;

	Double						averageApplicationsPerEntrepreneur;

	Double						averageApplicationsPerInvestor;

}
