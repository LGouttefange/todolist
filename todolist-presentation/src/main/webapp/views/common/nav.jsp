<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<nav>
    <div class="nav-wrapper">
        <a href="#!" class="brand-logo">Todolist</a>
        <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
        <ul class="right hide-on-med-and-down">
            <li><a href="<c:url value="/categories"/>">Categories</a></li>
            <li><a href="<c:url value="/tasks/get"/>">Tâches</a></li>
            <li><a href="<c:url value="/tasks/get/user"/>">Tâches d'un utilisateur</a></li>
        </ul>
        <ul class="side-nav" id="mobile-demo">
            <li><a href="<c:url value="/categories"/>">Categories</a></li>
            <li><a href="<c:url value="/tasks/get"/>">Tâches</a></li>
            <li><a href="<c:url value="/tasks/get/user"/>">Tâches d'un utilisateur</a></li>
        </ul>
    </div>
</nav>