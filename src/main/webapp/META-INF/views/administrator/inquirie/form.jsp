<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-url code="administrator.inquirie.form.label.title" path="title" placeholder = " "/>
	<jstl:if test="${command != 'create'}">
	<acme:form-url code="administrator.inquirie.form.label.creationDate" path="creationDate" readonly="true"/>
	</jstl:if>
	<acme:form-url code="administrator.inquirie.form.label.endDate" path="endDate" placeholder = "yyyy/mm/dd hh:mm"/>
	<acme:form-url code="administrator.inquirie.form.label.description" path="description" placeholder = " "/>
	<acme:form-url code="administrator.inquirie.form.label.minMoney" path="minMoney" placeholder = "10.00 EUR"/>
	<acme:form-url code="administrator.inquirie.form.label.maxMoney" path="maxMoney" placeholder = "10.00 EUR"/>
	<acme:form-url code="administrator.inquirie.form.label.email" path="email" placeholder = "us@gmail.com"/>
	
	<acme:form-submit test="${command == 'show' }"
		code="administrator.inquirie.form.button.update" 
		action="/administrator/inquirie/update"/>
	<acme:form-submit test="${command == 'show' }"
		code="administrator.inquirie.form.button.delete" 
		action="/administrator/inquirie/delete"/>
	<acme:form-submit test="${command == 'create' }"
		code="administrator.inquirie.form.button.create" 
		action="/administrator/inquirie/create"/>
	<acme:form-submit test="${command == 'update' }"
		code="administrator.inquirie.form.button.update" 
		action="/administrator/inquirie/update"/>
	<acme:form-submit test="${command == 'delete' }"
		code="administrator.inquirie.form.button.delete" 
		action="/administrator/inquirie/delete"/>
	
	<acme:form-return code="administrator.inquirie.form.button.return"/>
</acme:form>