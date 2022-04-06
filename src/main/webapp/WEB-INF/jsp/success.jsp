<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Statement Page</title>
</head>
<body>
	${success}
	<br/>
	<br/>
	<c:if test="${pageContext.request.userPrincipal.name=='admin'}">
		Go back to 	<a href="<c:url value='/showAdminStatements'/>">Back</a>
	</c:if>
	<c:if test="${pageContext.request.userPrincipal.name=='user'}">
		Go back to <a href="<c:url value='/showUserStatements' />">Back</a>
	</c:if>
	<ul>
		<table>
			<tr>
				<td>Statement ID</td><td>Date</td><td>Amount</td><td>Acc Number</td><td></td>
			</tr>
			<c:forEach items="${accountStatement}" var="accountStatement">
				<tr>
					<td>${accountStatement.ID}</td>
					<td>${accountStatement.datefield}</td>
					<td>${accountStatement.amount}</td>
					<td>${accountStatement.accountOBj.account_number}</td>
				</tr>
			</c:forEach>
		</table>
	</ul>
</body>

</html>