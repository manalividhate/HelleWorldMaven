<!DOCTYPE html>
<html lang="en">
 <head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
     <title></title>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
  </head>
  <body>
  <%
      if (session.getAttribute("message")==null){
          response.sendRedirect("login-form.jsp");
      }
  %>
<div class="container">
<div class="table-responsive">
  <h1>Dashboard</h1>

   <table class="table table-striped table-bordered">
    <thead>
    <tr>
          <th>Id</th>
          <th>First_Name</th>
          <th>Last_Name</th>
          <th>Mob_Number</th>
          <th>Email</th>
          <th>City</th>
    </tr>
    <c:forEach var="customer" items="${customers}">
        <tr>
            <td>${customer.getId()}</td>
            <td>${customer.getFirstName()}</td>
            <td>${customer.getLastName()}</td>
            <td>${customer.getMobNumber()}</td>
            <td>${customer.getEmail()}</td>
            <td>${customer.getCity()}</td>
        </tr>
    </c:forEach>

   </thead>
    </table>
    </div
    </div>
      <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
   </html>