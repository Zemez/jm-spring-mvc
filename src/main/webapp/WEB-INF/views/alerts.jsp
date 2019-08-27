<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="error" type="com.lang.String"--%>
<c:if test="${not empty sessionScope.error}">
  <p class="error">Session Error: ${sessionScope.error}</p>
  <c:remove var="error" scope="session"/>
</c:if>
<c:if test="${not empty requestScope.error}">
  <p class="error">Request Error: ${requestScope.error}</p>
</c:if>
<c:if test="${not empty param.error}">
  <p class="error">Error: ${SPRING_SECURITY_LAST_EXCEPTION.message}</p>
</c:if>
<%--@elvariable id="message" type="com.lang.String"--%>
<c:if test="${not empty sessionScope.message}">
  <p class="message">Session Message: ${sessionScope.message}</p>
  <c:remove var="message" scope="session"/>
</c:if>
<c:if test="${not empty requestScope.message}">
  <p class="message">Request Message: ${requestScope.message}</p>
</c:if>
