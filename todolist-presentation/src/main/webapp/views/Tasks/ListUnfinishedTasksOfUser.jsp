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

<!-- Liste des tâches -->
<section class="row">
    <c:forEach items="${tasks}" var="task">
        <div class="card-panel  grey  lighten-2 col s12 m6 l3">
            <h3>${task.label}</h3>
            <div class="row">
                <span class="col s6 m4 l2">
                        <fmt:formatDate value="${task.getBeginningDate()}" pattern="dd/MM/yyyy" />
                </span>
                <span class="col s6   m4 offset-m4 l3 offset-l6">
                        <fmt:formatDate value="${task.getEstimatedEndingDate()}" pattern="dd/MM/yyyy" />
                </span>
            </div>
            <div class="progress">
                <div class="determinate" style="width: ${currentProgressOfTasks.get(task.getId())}%"></div>
            </div>
        </div>
    </c:forEach>
</section>

<!-- Bouton "Finir les tâches" -->
<c:if test="${tasks != null && ! tasks.isEmpty()}">
    <section class="row">
        <div class="col s2 m2 l2">
            <form action="user/finishTasks/${userId}" method="post">
                <input type="button" class="waves-effect waves-light btn" onclick="submitFormIfConfirm(this.form)"
                       value="Finir toutes les tâches de l'utilisateur">

            </form>
        </div>

    </section>
</c:if>
<h2>Filtrer par date estimée de fin</h2>
<!-- Formulaire de selection des dates -->
<section class="row">

    <form action="" method="get">
        <section class="row">
            <div class="col s3">
                <label>
                    Date de début
                    <input type="date" name="beginningDate" class="datepicker" required>
                </label>
            </div>
            <div class="col s3">
                <label>
                    Date de fin
                    <input type="date" name="endingDate" class="datepicker" required>
                </label>
            </div>
        </section>

        <div class="row">
            <div class="col s3">
                <input type="submit" class="btn-large" value="Rechercher">
            </div>
        </div>
    </form>
</section>

<section class="row">
    <div class="col s2 m2 l2">
        <a class="waves-effect waves-light btn" href="<c:url value="/tasks/" />">Retour</a>
    </div>
</section>

<%@include file="../common/css.jsp" %>

<script>
    $(document).ready(function () {
        $('select').material_select();
    });
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 15 // Creates a dropdown of 15 years to control year
    });
</script>
</body>
</html>
