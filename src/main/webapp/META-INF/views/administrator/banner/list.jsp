<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.banner.list.label.slogan" path="slogan" width="60%" />
	<acme:list-column code="administrator.banner.list.label.brand" path="brand" width="40%" />
</acme:list>