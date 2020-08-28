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

	<acme:form-textbox code="administrator.tool-record.form.label.title" path="title" />
	<acme:form-textbox code="administrator.tool-record.form.label.sector" path="sector" />
	<acme:form-textbox code="administrator.tool-record.form.label.inventor" path="inventor" />
	<acme:form-textarea code="administrator.tool-record.form.label.description" path="description" />
	<acme:form-url code="administrator.tool-record.form.label.web" path="web" />
	<acme:form-textbox code="administrator.tool-record.form.label.email" path="email" placeholder="johndoe@mail.com"/>
	<acme:form-textbox code="administrator.tool-record.form.label.indication" path="indication" placeholder="open-source or closed-source"/>
	<acme:form-textbox code="administrator.tool-record.form.label.stars" path="stars" placeholder="-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5"/>
	
	
	<acme:form-submit test="${command == 'show' }" code="administrator.tool-record.form.button.update"
		action="/administrator/tool-record/update" />
	<acme:form-submit test="${command == 'show' }" code="administrator.tool-record.form.button.delete"
		action="/administrator/tool-record/delete" />
	<acme:form-submit test="${command == 'create' }" code="administrator.tool-record.form.button.create"
		action="/administrator/tool-record/create" />
	<acme:form-submit test="${command == 'update' }" code="administrator.tool-record.form.button.update"
		action="/administrator/tool-record/update" />
	<acme:form-submit test="${command == 'delete' }" code="administrator.tool-record.form.button.delete"
		action="/administrator/tool-record/delete" />
	
	
	<acme:form-return code="administrator.tool-record.form.button.return" />
	
</acme:form>