<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<%@include file="../common/head.jsp" %>
<body>
<%@include file="../common/nav.jsp"%>

<ul class="collection">
    <c:forEach items="${tasks}" var="task">
        <li class="collection-item">
                ${task.label} commençant le
            <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${task.beginningDate}"/></li>
    </c:forEach>
</ul>


<%@include file="../common/css.jsp"%>

</body>
</html>