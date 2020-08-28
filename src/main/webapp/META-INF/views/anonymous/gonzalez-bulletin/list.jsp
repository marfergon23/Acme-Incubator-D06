<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.gonzalez-bulletin.list.label.moment" path="moment" width="20%" />
	<acme:list-column code="anonymous.gonzalez-bulletin.list.label.movie" path="movie" width="40%" />
	<acme:list-column code="anonymous.gonzalez-bulletin.list.label.director" path="director" width="40%" />
</acme:list>