<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<form:form action="${empty user.id ? '/user/create' : '/user/update'}" method="post" commandName="user">
    <table>
        <c:if test="${not empty user.id}">
            <tr>
                <td><form:label path="id">Id:</form:label></td>
                <td><form:input path="id" readonly="true"/></td>
            </tr>
        </c:if>
        <tr>
            <td><form:label path="login">Login:</form:label></td>
            <td><form:input path="login" readonly="${empty user.id ? false : true}"/></td>
        </tr>
        <tr>
            <td><form:label path="password">Password:</form:label></td>
            <td><form:input path="password"/></td>
        </tr>
        <tr>
            <td><form:label path="name">Name:</form:label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="email">Email:</form:label></td>
            <td><form:input path="email"/></td>
        </tr>
    </table>
    <p>
        <form:button value="${empty user.id ? 'create' : 'update'}">${empty user.id ? 'create' : 'update'}</form:button>
        <c:if test="${not empty user.id}">
            <form:button value="delete" formaction="/user/delete">delete</form:button>
        </c:if>
    </p>
</form:form>
</body>
</html>
