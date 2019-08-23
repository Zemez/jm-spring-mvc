<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="error" type="com.lang.String"--%>
<c:if test="${not empty error}">
  <p class="error"><c:out value="Error: ${error}"/></p>
</c:if>
<%--@elvariable id="note" type="com.lang.String"--%>
<c:if test="${not empty note}">
  <p class="note"><c:out value="Note: ${note}"/></p>
</c:if>
