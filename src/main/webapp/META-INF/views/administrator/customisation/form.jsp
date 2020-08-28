<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.customisation.form.label.spamwords" path="spamwords"/>
	<acme:form-double code="administrator.customisation.form.label.threshold" path="threshold" placeholder = "0.00-100.00"/>
	<acme:form-textbox code="administrator.customisation.form.label.activitySectors" path="activitySectors"/>
	
	<acme:form-submit code="administrator.customisation.form.button.update" action="/administrator/customisation/update"/>
	
	<acme:form-return code="administrator.customisation.form.button.return"/>
</acme:form>