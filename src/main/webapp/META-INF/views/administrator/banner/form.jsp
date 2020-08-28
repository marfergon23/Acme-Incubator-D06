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

	<acme:form-url code="administrator.banner.form.label.picture" path="picture" />
	<acme:form-textbox code="administrator.banner.form.label.slogan" path="slogan" />
	<acme:form-url code="administrator.banner.form.label.url" path="url" />
	<acme:form-textbox code="administrator.banner.form.label.holderName" path="holderName" />
	<acme:form-textbox code="administrator.banner.form.label.number" path="number" placeholder="1111 2222 3333 4444"/>
	<acme:form-textbox code="administrator.banner.form.label.brand" path="brand" />
	<acme:form-integer code="administrator.banner.form.label.monthExpiration" path="monthExpiration" placeholder="1 - 12"/>
	<acme:form-integer code="administrator.banner.form.label.yearExpiration" path="yearExpiration" placeholder="2021"/>
	<acme:form-textbox code="administrator.banner.form.label.cvv" path="cvv" placeholder="123"/>
	
	
	<acme:form-submit test="${command == 'show' }" code="administrator.banner.form.button.update"
		action="/administrator/banner/update" />
	<acme:form-submit test="${command == 'show' }" code="administrator.banner.form.button.delete"
		action="/administrator/banner/delete" />
	<acme:form-submit test="${command == 'update' }" code="administrator.banner.form.button.update"
		action="/administrator/banner/update" />
	<acme:form-submit test="${command == 'delete' }" code="administrator.banner.form.button.delete"
		action="/administrator/banner/delete" />
	
	
	<acme:form-return code="administrator.banner.form.button.return" />
	
</acme:form>