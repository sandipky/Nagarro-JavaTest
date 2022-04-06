<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="border: 1px solid #ccc; padding: 5px; margin-bottom: 20px;">

	<a href="${pageContext.request.contextPath}/welcome">Home</a> | &nbsp;

    <c:if test="${pageContext.request.userPrincipal.name=='admin'}">
        Ok you’re administrator!
        <a href="${pageContext.request.contextPath}/showAdminStatements">Show Bank Statement</a>
    </c:if>
    <c:if test="${pageContext.request.userPrincipal.name=='user'}">
        <a href="${pageContext.request.contextPath}/showUserStatements">Show Bank Statement</a></c:if> | <u><h3 style="color: red;">
            <a onclick="document.forms['logoutForm'].submit()">Logout</a>
            </h3></u>

    <form id="logoutForm" method="POST" action="${contextPath}/logout">
    </form>

</div>