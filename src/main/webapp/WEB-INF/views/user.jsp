<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Page</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>"/>
</head>
<body>
<c:import url="nav.jsp"/>
<c:import url="alerts.jsp"/>
<h3>User:</h3>
<%--@elvariable id="user" type="com.javamentor.jm_spring_mvc.model.User"--%>
<%--@elvariable id="roles" type="java.util.List<Role>"--%>
<c:set var="roleUrl" value="${pageContext.request.isUserInRole('ADMIN') ? '/admin' : '/user'}"/>
<c:set var="updateUrl" value="${roleUrl}/update"/>
<form:form action="${empty user.id ? '/auth/signup' : updateUrl}" method="post" modelAttribute="user">
    <table>
        <c:if test="${not empty user.id}">
            <tr>
                <th><form:label path="id">Id:</form:label></th>
                <td><form:input path="id" readonly="true"/></td>
            </tr>
        </c:if>
        <tr>
            <th><form:label path="username">Username:</form:label></th>
            <td><form:input path="username" readonly="${empty user.id ? false : true}"/></td>
        </tr>
        <tr>
            <th><form:label path="password">Password:</form:label></th>
            <td><form:input path="password" type="password"/></td>
        </tr>
        <tr>
            <th><form:label path="roles">Roles:</form:label></th>
            <td><form:select path="roles" multiple="true" items="${roles}" itemValue="name" itemLabel="name"/></td>
        </tr>
        <tr>
            <th><form:label path="firstName">First name:</form:label></th>
            <td><form:input path="firstName"/></td>
        </tr>
        <tr>
            <th><form:label path="lastName">Last name:</form:label></th>
            <td><form:input path="lastName"/></td>
        </tr>
        <tr>
            <th><form:label path="email">Email:</form:label></th>
            <td><form:input path="email"/></td>
        </tr>
    </table>
    <p>
        <form:button value="${empty user.id ? 'create' : 'update'}">${empty user.id ? 'create' : 'update'}</form:button>
        <c:if test="${not empty user.id}">
            <form:button value="delete" formaction="${roleUrl}/delete">delete</form:button>
        </c:if>
    </p>
</form:form>
</body>
</html>
