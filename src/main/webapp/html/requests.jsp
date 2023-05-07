<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>My Requests</title>
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
      <div class="text-center my-5">
        <h1>Your Requests</h1>
        <p>Here you can see all requests you created</p>
      </div>

      <jsp:useBean id="requests" scope="session" type="java.util.List<org.stoliarchuk.entities.Request>"/>

      <c:if test="${not empty requests}">
      <table class="table">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Product Name</th>
            <th scope="col">Product Model</th>
            <th scope="col">Description</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="request" items="${requests}">
          <tr>
            <th scope="row"><c:out value="${request.requestId}"/></th>
            <td><c:out value="${request.productName}"/></td>
            <td><c:out value="${request.productModel}"/></td>
            <td><c:out value="${request.requestDescription}"/></td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
      </c:if>

      <c:if test="${empty requests}">
       <p class="text-center text-uppercase fs-2 py-5 text-danger fw-bold">You have not created any requests yet.</p>
      </c:if>
    </div>
  </body>
</html>