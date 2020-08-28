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

	<acme:form-textbox code="patron.creditCard.label.holder" path="holder" />
	<acme:form-textbox code="patron.creditCard.label.number" path="number" />
	<acme:form-textbox code="patron.creditCard.label.brand" path="brand" />
	<acme:form-textbox code="patron.creditCard.label.month" path="month" />
	<acme:form-textbox code="patron.creditCard.label.year" path="year" />
	<acme:form-textbox code="patron.creditCard.label.cvv" path="cvv" />

	<acme:form-submit method="post" test="${command == 'create'}" code="patron.creditCard.form.button.create" action="/patron/credit-card/create" />
	<input id="banner" name="banner" value="${banner}" type="hidden" />
	<input id="creditCard" name="creditCard" value="${creditCard}" type="hidden" />
	<input id="patron" name="patron" value="${patron}" type="hidden" />
	
	<acme:form-return code="patron.creditCard.button.return" />
	
</acme:form>