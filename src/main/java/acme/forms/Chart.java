package acme.forms;

import java.io.Serializable;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Chart implements Serializable{

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;


	Object[]					numberOfTechonologiesGroupedBySector;
	Object[]					numberOfToolsGroupedBySector;
	Object[]					ratioOfTechnologiesGroupedByStatus;
	Object[]					ratioOfToolsGroupedByStatus;
	Object[] 					ratioOfInvestmentRoundGroupedByKindRound;	
	Object[] 					ratioOfApplicationsGroupedByStatement;
	Object[]					numberOfRejectedApplicationsLastThreeWeeks;
	Object[]					numberOfPendingApplicationsLastThreeWeeks;
	Object[]					numberOfAcceptedApplicationsLastThreeWeeks;
	String[]					allDatesBeforeThreeWeeks;
	
		
	
					

}
