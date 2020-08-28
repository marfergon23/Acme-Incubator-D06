<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.gonzalez-bulletin.form.label.movie" path="movie"/>
	<acme:form-textarea code="anonymous.gonzalez-bulletin.form.label.director" path="director"/>
	
	<acme:form-submit code="anonymous.gonzalez-bulletin.form.button.create" action="/anonymous/gonzalez-bulletin/create"/>
	<acme:form-return code="anonymous.gonzalez-bulletin.form.button.return"/>
</acme:form>