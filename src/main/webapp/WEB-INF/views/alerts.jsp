<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="error" type="com.lang.String"--%>
<c:if test="${not empty sessionScope.error}">
  <p class="error">Error: ${sessionScope.error}</p>
  <c:remove var="error" scope="session"/>
</c:if>
<c:if test="${not empty error}">
  <p class="error"><c:out value="Error: ${error}"/></p>
</c:if>
<c:if test="${not empty param.error}">
  <p class="error">Error: ${SPRING_SECURITY_LAST_EXCEPTION.message}</p>
</c:if>
<%--@elvariable id="note" type="com.lang.String"--%>
<c:if test="${not empty note}">
  <p class="note">Message: ${note}</p>
</c:if>
<c:if test="${not empty sessionScope.message}">
  <p class="note">Message: ${sessionScope.message}</p>
  <c:remove var="message" scope="session"/>
</c:if>
