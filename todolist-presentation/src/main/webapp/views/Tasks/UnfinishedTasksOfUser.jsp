<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<%@include file="../common/head.jsp" %>
<body>
<%@include file="../common/nav.jsp"%>

<section class="row">
    <c:forEach items="${tasks}" var="task">
        <div class="card-panel  lighten lighten-2 col s12 m6 l3">
            <h2>${task.label}</h2>
            <div class="row">
                <span class="col s6 m4 l2">
                    ${task.getBeginningDateWithoutTime()}
                </span>
                <span class="col s6   m4 offset-m4 l3 offset-l6">
                        ${task.getEstimatedEndingDateWithoutTime()}
                </span>
            </div>
            <div class="progress">
                <div class="determinate" style="width: ${task.progressAsPercent()}%"></div>
            </div>
        </div>
    </c:forEach>
</section>

<c:if test="${tasks != null && ! tasks.isEmpty()}">
    <section class="row">
        <div class="col s2 m2 l2">
            <form action="user-finishTasks-${userId}" method="post">
                <input type="button" class="waves-effect waves-light btn" onclick="submitFormIfConfirm(this.form)" value="Finir toutes les tâches de l'utilisateur">

            </form>
        </div>

    </section>
</c:if>
<section class="row">
    <div class="col s2 m2 l2">
        <a class="waves-effect waves-light btn" href="user">Retour</a>
    </div>

</section>

<%@include file="../common/css.jsp"%>

<script>
    $(document).ready(function () {
        $('select').material_select();
    });</script>
</body>
</html>
