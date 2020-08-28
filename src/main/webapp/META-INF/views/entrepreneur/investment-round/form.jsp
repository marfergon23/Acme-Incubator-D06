<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.ticker" path="ticker" placeholder = "TEC-20-012345, SCI-20-999999..."/>
	<acme:form-moment code="entrepreneur.investmentRound.form.label.moment" path="moment" readonly="true" />
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.kindRound" path="kindRound" />
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.title" path="title" />
	<acme:form-textarea code="entrepreneur.investmentRound.form.label.description" path="description" />
	<acme:form-money code="entrepreneur.investmentRound.form.label.amountMoney" path="amountMoney" />
	<acme:form-url code="entrepreneur.investmentRound.form.label.link" path="link" />
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="entrepreneur.investmentRound.form.label.finalMode" path="finalMode" readonly="true" />
	</jstl:if>

	<p></p>

	<jstl:if test="${command == 'show'}">
		<a href=/acme-incubator/entrepreneur/work-programme/list?id=${id}><acme:message code="entrepreneur.workProgramme.list" /></a>
	</jstl:if>

	<p></p>


	<jstl:if test="${finalMode == false}">
		<acme:form-return code="entrepreneur.work-programme.form.button.create-workProgramme"
			action="/entrepreneur/work-programme/create?investmentRoundId=${id}" />
	</jstl:if>

	<acme:form-submit test="${command == 'show' && finalMode == false}" code="entrepreneur.investment-round.form.button.update"
		action="/entrepreneur/investment-round/update" />
	<acme:form-submit test="${command == 'show'}" code="entrepreneur.investment-round.form.button.delete"
		action="/entrepreneur/investment-round/delete" />
	<acme:form-submit test="${command == 'create'}" code="entrepreneur.investment-round.form.button.create"
		action="/entrepreneur/investment-round/create" />
	<acme:form-submit test="${command == 'update' && finalMode == false}" code="entrepreneur.investment-round.form.button.update"
		action="/entrepreneur/investment-round/update" />
	<acme:form-submit test="${command == 'delete'}" code="entrepreneur.investment-round.form.button.delete"
		action="/entrepreneur/investment-round/delete" />
<acme:form-return code="entrepreneur.investment-round.form.label.accounting-record" action="/entrepreneur/accounting-record/list?investmentId=${id}"/>
	
	<acme:form-return code="entrepreneur.investmentRound.form.button.return" />
</acme:form>

