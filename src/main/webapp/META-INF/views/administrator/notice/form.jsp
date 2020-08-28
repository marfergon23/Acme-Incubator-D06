<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${command != 'create'}">
	<acme:form-moment code="administrator.notice.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	
	<acme:form-url code="administrator.notice.form.label.headerPicture" path="headerPicture"/>
	<acme:form-textbox code="administrator.notice.form.label.title" path="title"/>
	<acme:form-moment code="administrator.notice.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="administrator.notice.form.label.body" path="body"/>
	<acme:form-url code="administrator.notice.form.label.links" path="links"/>
	
	
	<jstl:if test="${command == 'create'}">
	<acme:form-checkbox code="administrator.notice.form.label.checkbox" path="accept"/>
	<acme:form-submit  code="administrator.notice.form.button.create" action="/administrator/notice/create"/>
	</jstl:if>
	<acme:form-return code="administrator.notice.form.button.return"/>
</acme:form>