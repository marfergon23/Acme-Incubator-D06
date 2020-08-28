<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.fernandez-bulletin.form.label.nameCharacter" path="nameCharacter"/>
	<acme:form-textarea code="anonymous.fernandez-bulletin.form.label.skills" path="skills"/>
	
	<acme:form-submit code="anonymous.fernandez-bulletin.form.button.create" action="/anonymous/fernandez-bulletin/create"/>
	<acme:form-return code="anonymous.fernandez-bulletin.form.button.return"/>
</acme:form>