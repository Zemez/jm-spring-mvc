<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <table>
    <thead>
      <tr><th>Id</th><th>Login</th><th>Password</th><th>Name</th><th>Email</th></tr>
    </thead>
    <tbody>
      <c:forEach var="user" items="${users}">
        <tr>
          <td><a href=<c:url value="/user?id=${user.id}"/>><c:out value="${user.id}"/></a></td>
<%--          <td><a href=<c:url value="/user?login=${user.login}"/>><c:out value="${user.login}"/></a></td>--%>
          <td><c:out value="${user.login}" /></td>
          <td><c:out value="${user.password}" /></td>
          <td><c:out value="${user.name}" /></td>
          <td><c:out value="${user.email}" /></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
