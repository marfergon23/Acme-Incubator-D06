<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-url code="authenticated.investmentRound.form.label.ticker" path="ticker"/>
	<acme:form-url code="authenticated.investmentRound.form.label.moment" path="moment"/>
	<acme:form-url code="authenticated.investmentRound.form.label.kindRound" path="kindRound"/>
	<acme:form-url code="authenticated.investmentRound.form.label.title" path="title"/>
	<acme:form-url code="authenticated.investmentRound.form.label.description" path="description"/>
	<acme:form-url code="authenticated.investmentRound.form.label.amountMoney" path="amountMoney"/>
	<acme:form-url code="authenticated.investmentRound.form.label.link" path="link"/>
	
	<a href=/acme-incubator/authenticated/work-programme/list?id=${id}><acme:message code="authenticated.workProgramme.list"/></a>
	<p></p>
	<jstl:if test="${cantidadForums == 0}">
		<acme:form-return code="authenticated.forum.form.button.create-forum" action="/authenticated/forum/create?investmentRoundId=${id}"/>
	</jstl:if>
	<acme:form-return code="authenticated.investment-round.form.label.accounting-record" action="/authenticated/accounting-record/list?investmentId=${id}"/>
	

	
	<acme:form-return code="authenticated.forum.form.button.list-mine-forum" action="/authenticated/forum/list-mine"/>
	<acme:form-return code="authenticated.investmentRound.form.button.return"/>
</acme:form>