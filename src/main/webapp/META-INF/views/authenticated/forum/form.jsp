<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<input id="forumId" name="forumId" type="hidden" value="${param.forumId}"/>
	<acme:form-hidden path="investmentRoundId"/>
	<jstl:if test="${command == 'show'}">
		<acme:form-textbox code="authenticated.forum.form.label.title" path="title"/>
		<acme:form-textbox code="authenticated.forum.form.label.users" path="users"/>
		<acme:form-textbox code="authenticated.forum.form.label.investmentTitle" path="investmentRound.title"/>
		
		<acme:form-return code="authenticated.forum.form.button.goUpdate" action="/authenticated/forum/update?forumId=${id}"/>
		<acme:form-submit code="authenticated.forum.form.buttom.messages" action="/authenticated/message/list-mine?forumId=${id}" method="get" />
		<acme:form-submit code="authenticated.forum.form.button.delete" action="/authenticated/forum/delete"/>
		<acme:form-submit code="authenticated.forum.form.button.create" action="/authenticated/message/create?forumId=${id}" method="get"/>
		<acme:form-return code="authenticated.forum.form.button.return"/>
	</jstl:if>
	
	<jstl:if test="${command == 'create'}">
			<acme:form-textbox code="authenticated.forum.form.label.title" path="title"/>
			
			<acme:form-submit code="authenticated.forum.form.button.create" 
				action="/authenticated/forum/create"/>	
		<acme:form-return code="authenticated.forum.form.button.return"/>
	</jstl:if>
	
	<jstl:if test="${command == 'update'}">
		<acme:message code="authenticated.forum.form.message.adding"/>
		<p></p>
		<acme:message code="authenticated.forum.form.message.deleting"/>
		<acme:form-textbox code="authenticated.forum.form.label.users" path="users"/>
		
		<acme:form-submit code="authenticated.forum.form.button.update" 
			action="/authenticated/forum/update"/>	
		<acme:form-return code="authenticated.forum.form.button.return"/>
	</jstl:if>
</acme:form>