<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Repair Service</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>

    <body>
        <c:if test="${empty sessionScope.user}">
            <jsp:include page="/html/navbarnotloginned.jsp"/>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <jsp:include page="/html/navbarloginned.jsp"/>
        </c:if>
        <div class="container">
            <div class="row align-items-center min-vh-100">
                <div class="text-center my-5">
                    <h1>Repair Service</h1>
                    <p>Yelyzaveta Stoliarchuk</p>
                </div>
            </div>
        </div>
    </body>
</html>