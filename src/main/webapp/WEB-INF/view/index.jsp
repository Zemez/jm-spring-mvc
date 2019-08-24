<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Home Page</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>"/>
</head>
<body>
<c:import url="nav.jsp"/>
<c:import url="alerts.jsp"/>
<h3>Home:</h3>
<form:form action="/user/read" method="get">
  <label>User Id: <input type="number" name="id"/></label>
  <button type="submit">find</button>
</form:form>
</body>
</html>
