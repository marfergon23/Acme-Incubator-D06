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

	<acme:form-url code="patron.banner.form.label.picture" path="picture" />
	<acme:form-textbox code="patron.banner.form.label.slogan" path="slogan" />
	<acme:form-url code="patron.banner.form.label.url" path="url" />
	
	<jstl:if test="${command != 'create' and hasCreditCard}">
	
	<acme:form-textbox code="patron.creditCard.label.holder" path="creditCard.holder" readonly="true"/>
	<acme:form-textbox code="patron.creditCard.label.number" path="creditCard.number" readonly="true"/>
	<acme:form-textbox code="patron.creditCard.label.brand" path="creditCard.brand" readonly="true"/>
	<acme:form-textbox code="patron.creditCard.label.month" path="creditCard.month" readonly="true"/>
	<acme:form-textbox code="patron.creditCard.label.year" path="creditCard.year" readonly="true"/>
	<acme:form-textbox code="patron.creditCard.label.cvv" path="creditCard.cvv" readonly="true"/>
	<acme:form-submit method="get" code="patron.banner.form.button.creditCard.update" action="/patron/credit-card/update?creditCard=${creditCard}"/>	
	
	</jstl:if>

	<acme:form-submit method="post" test="${command == 'create'}" code="patron.banner.form.button.create"
		action="/patron/banner/create" />

	<acme:form-submit test="${command != 'create'}" code="patron.banner.form.button.update" action="/patron/banner/update" />

	<acme:form-submit test="${command != 'create'}" code="patron.banner.form.button.delete" action="/patron/banner/delete" />
	
	<jstl:if test="${command != 'create' and not hasCreditCard}">
		<acme:form-submit method="get" code="patron.banner.form.button.card.create" action="/patron/credit-card/create?banner=${banner}"/>	
	</jstl:if>
	
	<input id="banner" name="banner" value="${banner}" type="hidden" />
	<acme:form-return code="patron.banner.form.button.return" />
	
</acme:form>