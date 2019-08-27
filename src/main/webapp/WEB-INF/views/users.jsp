<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users Page</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>"/>
</head>
<body>
<c:import url="nav.jsp"/>
<c:import url="alerts.jsp"/>
<h3>Users:</h3>
<div>
    <table class="list">
        <thead>
        <tr>
            <th>Id</th>
            <th>Username</th>
            <th>Password</th>
            <th>Roles</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <%--@elvariable id="users" type="java.util.List<com.javamentor.jm_spring_mvc.model.User>"--%>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><a href="<c:url value="/admin?id=${user.id}"/>">${user.id}</a></td>
                    <%--<td><a href=<c:url value="/user?login=${user.login}"/>><c:out value="${user.login}"/></a></td>--%>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>
                    {${fn:join(user.roles.stream().sorted(r->r.id).map(r->r.name.toLowerCase()).toArray(),", ")}}
                </td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
