<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav> |
  <a href="<c:url value='/'/>">Home</a> |
  <a href="<c:url value='/user/create'/>">Create</a> |
  <a href="<c:url value='/user/all'/>">Users</a> |
  <%--  <a href="<c:url value='/signup'/>">SignUp</a> |--%>
  <%--  <c:choose>--%>
  <%--    <c:when test='${empty user}'>--%>
  <%--      <a href="<c:url value='/signin'/>">SignIn</a>--%>
  <%--    </c:when>--%>
  <%--    <c:otherwise>--%>
  <%--      <a href="<c:url value='/signout'/>">SignOut</a>--%>
  <%--    </c:otherwise>--%>
  <%--  </c:choose>--%>
</nav>
