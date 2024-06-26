<%@ page import="java.util.Set" %>
<%@ page import="jakarta.validation.ConstraintViolation" %><%--
  Created by IntelliJ IDEA.
  User: c425
  Date: 25.6.2024.
  Time: 00:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if (session.getAttribute("userBean") != null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<html>
<head>
    <title>PDS-Battleship Register</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="form-container flex-grow-1 d-flex flex-column justify-content-center align-items-center">
        <div class="mb-5 w-100">
            <h1 class="text-center mb-1">PDS-Atari</h1>
            <h3 class="text-center mb-3">Please register</h3>
        </div>

        <form method="post" action="register" class="w-100">
            <div class="form-floating mb-3">
                <input type="email" id="email" name="email" class="form-control" placeholder="pera@gmail.com">
                <label for="email">Email</label>
            </div>

            <div class="form-floating mb-3">
                <input type="text" id="firstName" name="firstName" class="form-control" placeholder="Pera">
                <label for="firstName">First Name</label>
            </div>

            <div class="form-floating mb-3">
                <input type="text" id="lastName" name="lastName" class="form-control" placeholder="Peric">
                <label for="lastName">Last Name</label>
            </div>

            <div class="form-floating mb-3">
                <input type="password" id="password" name="password" class="form-control" placeholder="PeraPeric">
                <label for="password">Password</label>
            </div>

            <div class="form-floating mb-3">
                <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" placeholder="PeraPeric">
                <label for="confirmPassword">Confirm password</label>
            </div>

            <div class="form-floating mb-3">
                <input type="number" id="budget" name="budget" class="form-control" placeholder="Enter your budget">
                <label for="budget">Budget</label>
            </div>

            <div class="d-flex">
                <button type="submit" class="btn btn-dark btn-block mb-4 flex-grow-1">Register</button>
            </div>

            <div class="text-center">
                <p>Already a member? <a href="login.jsp">Login</a></p>
                <p>Want to go back? <a href="index.jsp">Catalog</a></p>
            </div>
        </form>
    </div>

    <%
        if (request.getAttribute("violations") != null) {
            Set<String> violations = (Set<String>) request.getAttribute("violations");
    %>
    <div class="alert alert-danger">
        <ul>
            <% for (String violation : violations) { %>
            <li><%= violation %></li>
            <% } %>
        </ul>
    </div>
    <% } %>

    <script src="js/bootstrap.bundle.js"></script>
</body>
</html>
