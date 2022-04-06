<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="false"%>
<html>
<head>
<title>Bank Statements</title>
<style>

	.error {
		color: #ff0000;
	}
</style>
</head>
<body>
	<jsp:include page="menu.jsp" />

	<h3 style="color: red;">Account Statements</h3>

	<div id="userStatement">
		<form:form action="/getUserStatements" method="post" modelAttribute="account">
			<form:input type="hidden" path="ID" id="ID"/>
			<p>
				<label for="account_number">Enter Account Number</label>
				<form:input path="account_number" />
				<form:errors path="account_number" cssClass="error"/>

			</p>

			<input type="SUBMIT" value="Submit" />
		</form:form>
	</div>

</body>
</html>