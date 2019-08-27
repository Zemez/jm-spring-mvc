<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav>|
    <a href="<c:url value='/'/>">Home</a> |
    <%--@elvariable id="user" type="com.javamentor.jm_spring_mvc.model.User"--%>
    <c:if test="${not empty pageContext.request.getRemoteUser()}">
        <c:set var="roleUrl" value="${pageContext.request.isUserInRole('ADMIN') ? '/admin' : '/user'}"/>
        <a href="<c:url value="${roleUrl}" />">User</a> |
        <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
            <a href="<c:url value="${roleUrl}/all" />">Users</a> |
        </c:if>
    </c:if>
    <c:choose>
        <c:when test="${empty pageContext.request.getRemoteUser()}">
            <a href="<c:url value='/signin'/>">Sign-In</a> |
            <a href="<c:url value='/auth/signup'/>">Sign-Up</a> |
        </c:when>
        <c:otherwise>
            <a href="<c:url value='/signout'/>">Sign-Out</a> |
        </c:otherwise>
    </c:choose>
</nav>
