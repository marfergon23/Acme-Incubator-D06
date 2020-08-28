<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="investmentRoundId" />
	<acme:form-hidden path="finalMode" />

	<acme:form-textbox code="entrepreneur.workProgramme.form.label.title" path="title" />
	<acme:form-moment code="entrepreneur.workProgramme.form.label.moment" path="moment" readonly="true" />
	<acme:form-moment code="entrepreneur.workProgramme.form.label.deadline" path="deadline" />
	<acme:form-money code="entrepreneur.workProgramme.form.label.budget" path="budget" />

	<acme:form-submit test="${command == 'show' && finalMode == false}" code="entrepreneur.work-programme.form.button.update"
		action="/entrepreneur/work-programme/update" />
	<acme:form-submit test="${command == 'show' && finalMode == false}" code="entrepreneur.work-programme.form.button.delete"
		action="/entrepreneur/work-programme/delete" />
	<acme:form-submit test="${command == 'create'}" code="entrepreneur.work-programme.form.button.create"
		action="/entrepreneur/work-programme/create" />
	<acme:form-submit test="${command == 'update' && finalMode == false}" code="entrepreneur.work-programme.form.button.update"
		action="/entrepreneur/work-programme/update" />
	<acme:form-submit test="${command == 'delete' && finalMode == false}" code="entrepreneur.work-programme.form.button.delete"
		action="/entrepreneur/work-programme/delete" />

	<acme:form-return code="entrepreneur.workProgramme.form.button.return" />
</acme:form>
