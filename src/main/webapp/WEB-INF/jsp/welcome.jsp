<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false"%>
<html>
<head>
<title>Welcome</title>
</head>
<body>
	<jsp:include page="menu.jsp" />

	<h3 style="color: red;">Hello ,</h3><c:if test="${not empty pageContext.request.userPrincipal}">
		<c:out value="${pageContext.request.userPrincipal.name}" />
	</c:if>
</body>
</html>