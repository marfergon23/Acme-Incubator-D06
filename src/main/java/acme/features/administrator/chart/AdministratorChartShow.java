
package acme.features.administrator.chart;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Chart;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorChartShow implements AbstractShowService<Administrator, Chart> {

	@Autowired
	AdministratorChartRepository repository;


	@Override
	public boolean authorise(final Request<Chart> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Chart> request, final Chart entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numberOfTechonologiesGroupedBySector", "numberOfToolsGroupedBySector");
		request.unbind(entity, model, "ratioOfTechnologiesGroupedByStatus", "ratioOfToolsGroupedByStatus");
		request.unbind(entity, model, "ratioOfInvestmentRoundGroupedByKindRound", "ratioOfApplicationsGroupedByStatement");
		request.unbind(entity, model, "numberOfRejectedApplicationsLastThreeWeeks");
		request.unbind(entity, model, "numberOfPendingApplicationsLastThreeWeeks");
		request.unbind(entity, model, "numberOfAcceptedApplicationsLastThreeWeeks");
		request.unbind(entity, model, "allDatesBeforeThreeWeeks");
	}

	@Override
	public Chart findOne(final Request<Chart> request) {
		assert request != null;


		Chart d = new Chart();
		Object[] companiesBySector = this.repository.findTechnologiesSector();
		d.setNumberOfTechonologiesGroupedBySector(companiesBySector);
		Object[] investorsBySector = this.repository.findToolsSector();
		d.setNumberOfToolsGroupedBySector(investorsBySector);
		Object[] technologiessByStatus = this.repository.findTechnologiesStatus();
		d.setRatioOfTechnologiesGroupedByStatus(technologiessByStatus);
		Object[] toolsByStatus = this.repository.findToolsStatus();
		d.setRatioOfToolsGroupedByStatus(toolsByStatus);
		Object[] investmentRoundByKindRound = this.repository.findInvestmentRoundKindRound();
		d.setRatioOfInvestmentRoundGroupedByKindRound(investmentRoundByKindRound);
		Object[] applicationsByStatement = this.repository.findApplicationStatement();
		d.setRatioOfApplicationsGroupedByStatement(applicationsByStatement);
		
		
		Calendar calendar;
		String[] allDatesBeforeThreeWeeks = new String[15];

		calendar = new GregorianCalendar();
		calendar.setTime(new Date(System.currentTimeMillis()));
		calendar.add(Calendar.DATE, -15);
		Object[] rejectedApplicationsByDays = this.repository.findRejectedApplicationsLastThreeWeeks(calendar.getTime());
		d.setNumberOfRejectedApplicationsLastThreeWeeks(rejectedApplicationsByDays);
		Object[] pendingApplicationsByDays = this.repository.findPendingApplicationsLastThreeWeeks(calendar.getTime());
		d.setNumberOfPendingApplicationsLastThreeWeeks(pendingApplicationsByDays);
		Object[] acceptedApplicationsByDays = this.repository.findAcceptedApplicationsLastThreeWeeks(calendar.getTime());
		d.setNumberOfAcceptedApplicationsLastThreeWeeks(acceptedApplicationsByDays);

		//Obteniendo todas las fechas de 15 días anteriores = 3 semanas anteriores
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		for (Integer i = 0; i < 15; i++) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			allDatesBeforeThreeWeeks[i] = formatoFecha.format(calendar.getTime());
		}
		d.setAllDatesBeforeThreeWeeks(allDatesBeforeThreeWeeks);

		
		return d;
	}


}
