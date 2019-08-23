<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form action="<c:url value="${empty user ? '/user/create' : '/user/update'}"/>" method="post">
<c:if test="${not empty user && not empty user.id}">
  <p><label>Id: <input type="text" name="id" value="${user.id}" readonly="readonly"></label></p>
</c:if>
  <p><label>Login: <input type="text" name="login" value="${empty user ? null : user.login}" ${empty user ? null : 'readonly=\"readonly\"'}/></label></p>
  <p><label>Password: <input type="text" name="password" value="${empty user ? null : user.password}"></label></p>
  <p><label>Name: <input type="text" name="name" value="${empty user ? null : user.name}"></label></p>
  <p><label>Email: <input type="text" name="email" value="${empty user ? null : user.email}"></label></p>
  <p><button type="submit">${empty user || empty user.id ? 'create' : 'update'}</button></p>
</form>
<c:if test="${not empty user && not empty user.id}">
<form action="<c:url value="/user/delete"/>" method="get">
  <input type="hidden" name="id" value="${user.id}">
  <p><button type="submit">delete</button></p>
</form>
</c:if>
</body>
</html>
