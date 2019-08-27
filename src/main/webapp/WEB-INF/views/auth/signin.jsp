<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>"/>
</head>
<body>
<c:import url="../nav.jsp"/>
<c:import url="../alerts.jsp"/>
<h3>Login:</h3>
<form action="<c:url value="/signin"/>" method="post">
    <table>
        <tr>
            <td><label for="username">Username:</label></td>
            <td><input id="username" type="text" name="username"/></td>
        </tr>
        <tr>
            <td><label for="password">Password:</label></td>
            <td><input id="password" type="password" name="password"/></td>
        </tr>
        <tr>
            <td><label for="remember">Remember:</label></td>
            <td><input id="remember" type="checkbox" name="remember"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">Login</button>
                <button type="reset">Reset</button>
            </td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
