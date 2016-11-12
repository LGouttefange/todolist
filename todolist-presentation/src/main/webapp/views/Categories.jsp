<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<%@include file="common/head.jsp" %>
<body>
<%@include file="common/nav.jsp"%>
	<ul class="collection">
		<c:forEach items="${categories}" var="category">
			<li class="collection-item">
				${category.name}
		</c:forEach>
	</ul>
	
