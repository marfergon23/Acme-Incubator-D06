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
	<acme:form-textbox code="authenticated.patron.form.label.organisation" path="organisation" />
	<jstl:if test="${command != 'create' and hasCreditCard}">
	
	<acme:form-textbox code="patron.creditCard.label.holder" path="card.holder" />
	<acme:form-textbox code="patron.creditCard.label.number" path="card.number" />
	<acme:form-textbox code="patron.creditCard.label.brand" path="card.brand" />
	<acme:form-textbox code="patron.creditCard.label.month" path="card.month" />
	<acme:form-textbox code="patron.creditCard.label.year" path="card.year"/>
	<acme:form-textbox code="patron.creditCard.label.cvv" path="card.cvv" />
	<acme:form-submit method="get" code="patron.banner.form.button.creditCard.update" action="/patron/credit-card-for-patron/update?card=${card}"/>	
	
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.patron.form.button.create"
		action="/authenticated/patron/create" />
	<acme:form-submit test="${command == 'update'}" code="authenticated.patron.form.button.update"
		action="/authenticated/patron/update" />
		
	<jstl:if test="${command != 'create' and !hasCreditCard}">
		<acme:form-submit method="get" code="patron.patron.form.button.card.create" action="/patron/credit-card-for-patron/create"/>	
	</jstl:if>
	
	<acme:form-return code="authenticated.patron.form.button.return" />
</acme:form>