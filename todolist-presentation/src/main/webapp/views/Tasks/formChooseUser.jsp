<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<%@include file="../common/head.jsp" %>
<body>
<%@include file="../common/nav.jsp" %>
<section>
    <ul class="collection">

        <c:forEach items="${users}" var="user">
            <li class="collection-item">
                <a href="users/${user.id}">${user.getName()}</a>
            </li>
        </c:forEach>
    </ul>


</section>

<%@include file="../common/css.jsp" %>


</body>

</html>
