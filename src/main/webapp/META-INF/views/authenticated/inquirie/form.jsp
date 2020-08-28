<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-url code="authenticated.inquirie.form.label.title" path="title"/>
	<acme:form-url code="authenticated.inquirie.form.label.creationDate" path="creationDate"/>
	<acme:form-url code="authenticated.inquirie.form.label.endDate" path="endDate"/>
	<acme:form-url code="authenticated.inquirie.form.label.description" path="description"/>
	<acme:form-url code="authenticated.inquirie.form.label.minMoney" path="minMoney"/>
	<acme:form-url code="authenticated.inquirie.form.label.maxMoney" path="maxMoney"/>
	<acme:form-url code="authenticated.inquirie.form.label.email" path="email"/>
	
	
	
	<acme:form-return code="authenticated.inquirie.form.button.return"/>
</acme:form>