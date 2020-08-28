<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-url code="anonymous.marin-bulletin.form.label.web" path="web"/>
	<acme:form-textarea code="anonymous.marin-bulletin.form.label.description" path="description"/>
	
	<acme:form-submit code="anonymous.marin-bulletin.form.button.create" action="/anonymous/marin-bulletin/create"/>
	<acme:form-return code="anonymous.marin-bulletin.form.button.return"/>
</acme:form>