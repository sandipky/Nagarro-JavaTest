<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bank Statement</title>
	<style>

		.error {
			color: #ff0000;
		}
	</style>
</head>
<jsp:include page="menu.jsp" />
<body>
	<h3 style="color: red;">Bank Statement</h3>

	<div id="adminStatement">
		<form:form action="/getAdminStatements" method="post"
			modelAttribute="statement">
			<p>
				<label>Enter Account Number</label>
				<form:input path="account_number" />
				<form:errors path="account_number" cssClass="error"/>
			</p>
			<p>
				<label>Enter amount range</label>
				<label> From </label>
				<form:input path="fromAmt" />
				<form:errors path="fromAmt" cssClass="error"/>
				<label> To </label>
				<form:input path="toAmt" />
				<form:errors path="toAmt" cssClass="error"/>
			</p>
			<p>
				<label>Enter Date range</label>
				<label> From </label>
				<form:input path="startDate" />
				<form:errors path="startDate" cssClass="error"/>
				<label> To </label>
				<form:input path="endDate" />
				<form:errors path="endDate" cssClass="error"/>
			</p>
			<input type="SUBMIT" value="Submit" />
		</form:form>
	</div>


</body>
</html>
