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

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-textbox code="bookkeeper.investment-round.form.label.ticker" path="ticker" />
	<acme:form-moment code="bookkeeper.investment-round.form.label.moment" path="moment" />
	<acme:form-textbox code="bookkeeper.investment-round.form.label.kindRound" path="kindRound" placeholder="SEED, ANGEL, SERIES-A, SERIES-B, SERIES-C or BRIDGE" />
	<acme:form-textbox code="bookkeeper.investment-round.form.label.title" path="title" />
	<acme:form-textarea code="bookkeeper.investment-round.form.label.description" path="description" />
	<acme:form-money code="bookkeeper.investment-round.form.label.amountMoney" path="amountMoney" placeholder="1 - 12"/>
	<acme:form-url code="bookkeeper.investment-round.form.label.link" path="link" />

	<acme:form-submit method="get" code="bookkeeper.investment-round.form.label.accounting-record" action="/bookkeeper/accounting-record/list?investmentId=${id}"/>
	
	<acme:form-submit method="get"  code="bookkeeper.investment-round.form.button.create" action="/bookkeeper/accounting-record/create?investmentId=${id} "/>
	<acme:form-return code="bookkeeper.investment-round.form.button.return" />
	
</acme:form>