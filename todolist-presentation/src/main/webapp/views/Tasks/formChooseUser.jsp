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
    <form action="" method="post">
        <section class="valign-wrapper row">

            <label class="valign col s6 offset-s3">
                <i class="material-icons prefix">account_circle</i>
                <em>Utilisateur</em>

                <select class="browser-default valign " name="userId" required>
                    <option value="0" disabled selected>Liste des utilisateurs</option>
                    <c:forEach items="${users}" var="user">
                        <option value="${user.id}">${user.name}</option>
                    </c:forEach>
                </select>
            </label>

        </section>

        <section class="row">

            <div class="col s3 offset-s3">
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
            <div class="col s3 offset-s3">
                <input type="submit" class="btn-large" value="Rechercher">
            </div>
        </div>
    </form>


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
