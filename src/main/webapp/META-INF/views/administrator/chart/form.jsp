<%--
	- form.jsp
	-
	- Copyright (c) 2019 Rafael Corchuelo.
	-
	- In keeping with the traditional purpose of furthering education and research, it is
	- the policy of the copyright owner to permit non-commercial use and redistribution of
	- this software. It has been tested carefully, but it is not guaranteed for any particular
	- purposes.  The copyright owner does not offer any warranties or representations, nor do
	- they accept any liabilities with respect to them.
	--%>
	

	<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	

	<div>
	<acme:message code="administrator.chart.form.label.technologiesBySector"/>
		<canvas id="numberOfTechonologiesGroupedBySector"></canvas>
	</div>
	<br></br>
	<div>
	<acme:message code="administrator.chart.form.label.toolsBySector"/>
	    <canvas id="numberOfToolsGroupedBySector"></canvas>
	</div>
	<br></br>
	<div>
	<acme:message code="administrator.chart.form.label.technologiesByStatus"/>
		<canvas id="ratioOfTechnologiesGroupedByStatus"></canvas>
	</div>
	<br></br>
	<div>
	<acme:message code="administrator.chart.form.label.toolsByStatus"/>
	    <canvas id="ratioOfToolsGroupedByStatus"></canvas>
	</div>
	<div>
	<acme:message code="administrator.chart.form.label.investmentRoundByKind"/>
		<canvas id="ratioOfInvestmentRoundGroupedByKindRound"></canvas>
	</div>
	<br></br>
	<div>
	<acme:message code="administrator.chart.form.label.ApplicationsByStatement"/>
    	<canvas id="ratioOfApplicationsGroupedByStatement"></canvas>
	</div>
<div>
<acme:message code="administrator.chart.form.label.TimeSeriesApplications"/>
    <canvas id="TimeSeriesApplications"></canvas>
</div>
	

	<script type ="text/javascript">
	 $(document).ready(function(){
		 var CanvasCompany = document.getElementById("numberOfTechonologiesGroupedBySector");
		 Chart.defaults.global.defaultFontFamily = "Modeka";
		 Chart.defaults.global.defaultFontSize = 15;
		 
		 var DataCompany = {
				 labels : [
					 <jstl:forEach items = "${numberOfTechonologiesGroupedBySector}" var="item">
					 "<jstl:out value= "${item[0]}" />" ,
					 </jstl:forEach>
				 ],
				 datasets:[
					 {
						 data: [
							 <jstl:forEach items= "${numberOfTechonologiesGroupedBySector}" var="item">
							 "<jstl:out value = "${item[1]}" />" ,
							 </jstl:forEach>
						 ],
						 backgroundColor :["blue", "red", "yellow", "green", "purple"]
					 }
				 ]
		 };
		 var pieChartCompany = new Chart(CanvasCompany, {
			 type: 'pie',
			 data: DataCompany
		 });
	 });
	 
	 $(document).ready(function(){
		 var CanvasInvestor = document.getElementById("numberOfToolsGroupedBySector");
		 Chart.defaults.global.defaultFontFamily = "Modeka";
		 Chart.defaults.global.defaultFontSize = 15;
		 
		 var DataInvestor = {
				 labels : [
					 <jstl:forEach items = "${numberOfToolsGroupedBySector}" var="item">
					 "<jstl:out value= "${item[0]}" />" ,
					 </jstl:forEach>
				 ],
				 datasets:[
					 {
						 data: [
							 <jstl:forEach items= "${numberOfToolsGroupedBySector}" var="item">
							 "<jstl:out value = "${item[1]}" />" ,
							 </jstl:forEach>
						 ],
						 backgroundColor :["blue", "red", "yellow", "green", "purple"]
					 }
				 ]
		 };
		 var pieChartInvestor = new Chart(CanvasInvestor, {
			 type: 'pie',
			 data: DataInvestor
		 });
	 });
	 
	 $(document).ready(function(){
		 var CanvasInvestor = document.getElementById("ratioOfTechnologiesGroupedByStatus");
		 Chart.defaults.global.defaultFontFamily = "Modeka";
		 Chart.defaults.global.defaultFontSize = 15;
		 
		 var DataInvestor = {
				 labels : [
					 <jstl:forEach items = "${ratioOfTechnologiesGroupedByStatus}" var="item">
					 "<jstl:out value= "${item[0]}" />" ,
					 </jstl:forEach>
				 ],
				 datasets:[
					 {
						 data: [
							 <jstl:forEach items= "${ratioOfTechnologiesGroupedByStatus}" var="item">
							 "<jstl:out value = "${item[1]}" />" ,
							 </jstl:forEach>
						 ],
						 backgroundColor :["blue", "red", "green"]
					 }
				 ]
		 };
		 var pieChartInvestor = new Chart(CanvasInvestor, {
			 type: 'pie',
			 data: DataInvestor
		 });
	 });
	 
	 $(document).ready(function(){
		 var CanvasInvestor = document.getElementById("ratioOfToolsGroupedByStatus");
		 Chart.defaults.global.defaultFontFamily = "Modeka";
		 Chart.defaults.global.defaultFontSize = 15;
		 
		 var DataInvestor = {
				 labels : [
					 <jstl:forEach items = "${ratioOfToolsGroupedByStatus}" var="item">
					 "<jstl:out value= "${item[0]}" />" ,
					 </jstl:forEach>
				 ],
				 datasets:[
					 {
						 data: [
							 <jstl:forEach items= "${ratioOfToolsGroupedByStatus}" var="item">
							 "<jstl:out value = "${item[1]}" />" ,
							 </jstl:forEach>
						 ],
						 backgroundColor :["blue", "red", "green"]
					 }
				 ]
		 };
		 var pieChartInvestor = new Chart(CanvasInvestor, {
			 type: 'pie',
			 data: DataInvestor
		 });
	 });
	 
	 $(document).ready(function(){
		 var CanvasInvestor = document.getElementById("ratioOfToolsGroupedByStatus");
		 Chart.defaults.global.defaultFontFamily = "Modeka";
		 Chart.defaults.global.defaultFontSize = 15;
		 
		 var DataInvestor = {
				 labels : [
					 <jstl:forEach items = "${ratioOfToolsGroupedByStatus}" var="item">
					 "<jstl:out value= "${item[0]}" />" ,
					 </jstl:forEach>
				 ],
				 datasets:[
					 {
						 data: [
							 <jstl:forEach items= "${ratioOfToolsGroupedByStatus}" var="item">
							 "<jstl:out value = "${item[1]}" />" ,
							 </jstl:forEach>
						 ],
						 backgroundColor :["blue", "red", "green"]
					 }
				 ]
		 };
		 var pieChartInvestor = new Chart(CanvasInvestor, {
			 type: 'pie',
			 data: DataInvestor
		 });
	 }); 
	 
	 $(document).ready(function(){
		 var CanvasInvestor = document.getElementById("ratioOfInvestmentRoundGroupedByKindRound");
		 Chart.defaults.global.defaultFontFamily = "Modeka";
		 Chart.defaults.global.defaultFontSize = 15;
		 
		 var DataInvestor = {
				 labels : [
					 <jstl:forEach items = "${ratioOfInvestmentRoundGroupedByKindRound}" var="item">
					 "<jstl:out value= "${item[0]}" />" ,
					 </jstl:forEach>
				 ],
				 datasets:[
					 {
						 data: [
							 <jstl:forEach items= "${ratioOfInvestmentRoundGroupedByKindRound}" var="item">
							 "<jstl:out value = "${item[1]}" />" ,
							 </jstl:forEach>
						 ],
						 backgroundColor :["blue", "red", "yellow", "green", "purple", "pink"]
					 }
				 ]
		 };
		 var pieChartInvestor = new Chart(CanvasInvestor, {
			 type: 'pie',
			 data: DataInvestor
		 });
	 });
	 
	 $(document).ready(function(){
		 var CanvasInvestor = document.getElementById("ratioOfApplicationsGroupedByStatement");
		 Chart.defaults.global.defaultFontFamily = "Modeka";
		 Chart.defaults.global.defaultFontSize = 15;
		 
		 var DataInvestor = {
				 labels : [
					 <jstl:forEach items = "${ratioOfApplicationsGroupedByStatement}" var="item">
					 "<jstl:out value= "${item[0]}" />" ,
					 </jstl:forEach>
				 ],
				 datasets:[
					 {
						 data: [
							 <jstl:forEach items= "${ratioOfApplicationsGroupedByStatement}" var="item">
							 "<jstl:out value = "${item[1]}" />" ,
							 </jstl:forEach>
						 ],
						 backgroundColor :["blue", "red", "green"]
					 }
				 ]
		 };
		 var pieChartInvestor = new Chart(CanvasInvestor, {
			 type: 'pie',
			 data: DataInvestor
		 });
	 });
	 
	 
	 $(document).ready(function(){
		 var CanvasApplicationsByDays = document.getElementById("TimeSeriesApplications");
		 Chart.defaults.global.defaultFontFamily = "Modeka";
		 Chart.defaults.global.defaultFontSize = 15;
		 
		 var DataApplicationsByDays = {
				 labels : [
					 <jstl:forEach items = "${allDatesBeforeThreeWeeks}" var="item">
					 "<jstl:out value= "${item}" />" ,
					 </jstl:forEach>
				 ],
				 datasets:[
					 {
						 data: [
							 <jstl:forEach items="${allDatesBeforeThreeWeeks}" var="item">
							 <jstl:set var="value" value="0"/>
							 <jstl:forEach items="${numberOfRejectedApplicationsLastThreeWeeks}" var="item2">
							   <jstl:if test="${item == item2[0]}">
							      <jstl:set var="value" value="${item2[1]}"/>
							   </jstl:if>
							 </jstl:forEach>
							 <jstl:out value="${value}"/>,
							 </jstl:forEach>
						 ],
						 borderColor:["red"],
		                 label:"<acme:message code='administrator.chart.form.label.RejectedAplications'/>"
					 },
					 {
					     data: [
					    	 <jstl:forEach items="${allDatesBeforeThreeWeeks}" var="item">
							 <jstl:set var="value" value="0"/>
							 <jstl:forEach items="${numberOfPendingApplicationsLastThreeWeeks}" var="item2">
							   <jstl:if test="${item == item2[0]}">
							      <jstl:set var="value" value="${item2[1]}"/>
							   </jstl:if>
							 </jstl:forEach>
							 <jstl:out value="${value}"/>,
							 </jstl:forEach>
					     ],
						 borderColor:["blue"],
	                     label:"<acme:message code='administrator.chart.form.label.PendingAplications'/>"
					 },
					 {
						 data: [
							 <jstl:forEach items="${allDatesBeforeThreeWeeks}" var="item">
							 <jstl:set var="value" value="0"/>
							 <jstl:forEach items="${numberOfAcceptedApplicationsLastThreeWeeks}" var="item2">
							   <jstl:if test="${item == item2[0]}">
							      <jstl:set var="value" value="${item2[1]}"/>
							   </jstl:if>
							 </jstl:forEach>
							 <jstl:out value="${value}"/>,
							 </jstl:forEach>
						 ],
						 borderColor:["green"],
						 label:"<acme:message code='administrator.chart.form.label.AcceptedAplications'/>",
					 }
				 ]
		 
		 };
					
		 var pieChartApplicationsByDays = new Chart(CanvasApplicationsByDays, {
			 type: 'line',
			 data: DataApplicationsByDays,
		 });
	 }); 

	 
	</script>

