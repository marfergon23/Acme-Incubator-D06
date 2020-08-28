<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.technology.form.label.title" path="title" />
	<acme:form-textbox code="administrator.technology.form.label.activitySector" path="activitySector" />
	<acme:form-textbox code="administrator.technology.form.label.inventorName" path="inventorName" />
	<acme:form-textarea code="administrator.technology.form.label.description" path="description" />
	<acme:form-url code="administrator.technology.form.label.webSite" path="webSite" />
	<acme:form-textbox code="administrator.technology.form.label.email" path="email" />
	<acme:form-textbox code="administrator.technology.form.label.indication" path="indication" />
	<acme:form-textbox code="administrator.technology.form.label.stars" path="stars" />

	<acme:form-submit test="${command == 'show'}" code="administrator.technology.form.button.update"
		action="/administrator/technology/update" />
	<acme:form-submit test="${command == 'show'}" code="administrator.technology.form.button.delete"
		action="/administrator/technology/delete" />
	<acme:form-submit test="${command == 'create'}" code="administrator.technology.form.button.create"
		action="/administrator/technology/create" />
	<acme:form-submit test="${command == 'update'}" code="administrator.technology.form.button.update"
		action="/administrator/technology/update" />
	<acme:form-submit test="${command == 'delete'}" code="administrator.technology.form.button.delete"
		action="/administrator/technology/delete" />
	<acme:form-return code="administrator.technology.form.button.return" />
</acme:form>
