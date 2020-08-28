<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.fernandez-bulletin.list.label.moment" path="moment" width="20%" />
	<acme:list-column code="anonymous.fernandez-bulletin.list.label.nameCharacter" path="nameCharacter" width="20%" />
	<acme:list-column code="anonymous.fernandez-bulletin.list.label.skills" path="skills" width="60%" />
</acme:list>