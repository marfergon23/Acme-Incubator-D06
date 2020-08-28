
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
<jstl:if test="${pending}">
<script type="text/javascript"> redirect('/authenticated/book-keeper-request/show')</script>
</jstl:if>
<acme:form >
		<jstl:if test="${command=='show'}">
	<jstl:if test="${status=='false'}">
	<acme:message code="authenticated.bookKeeperRequest.form.label.Rejected"/>
	</jstl:if>
	<jstl:if test="${status==null}">
	<acme:message code="authenticated.bookKeeperRequest.form.label.Pending"/>
	</jstl:if>
	</jstl:if>
    <jstl:if test="${status==true}">
    <acme:message code="authenticated.bookKeeperRequest.form.label.Accepted"/>
    </jstl:if>
	<acme:form-textbox code="authenticated.bookKeeperRequest.form.label.firmName" path="firmName"/>
	<acme:form-textarea code="authenticated.bookKeeperRequest.form.label.responsibilityStatement" path="responsibilityStatement"/>

	<acme:form-submit test="${command=='create'}" code="authenticated.bookKeeperRequest.form.button.create" action="/authenticated/book-keeper-request/create"/>
	<acme:form-return code="authenticated.bookKeeperRequest.form.button.return"/>
</acme:form>
