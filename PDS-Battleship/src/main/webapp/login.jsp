<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: c425
  Date: 24.6.2024.
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if (session.getAttribute("playerBean") != null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<html>
<head>
    <title>PDS-Battleship Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="form-container">
        <div class="mb-5">
            <h1 class="text-center mb-1">PDS-Battleship</h1>
            <h3 class="text-center mb-3">Please login</h3>
        </div>

        <form action="login" method="post">
            <div class="form-floating mb-3">
                <input type="text" id="username" name="username" class="form-control" placeholder="pera.peric">
                <label for="username">Username</label>
            </div>

            <div class="form-floating mb-3">
                <input type="password" id="password" name="password" class="form-control" placeholder="PeraPeric123">
                <label for="password">Password</label>
            </div>

            <div class="d-flex">
                <button type="submit" class="btn btn-primary btn-block mb-4 flex-grow-1">Login</button>
            </div>

            <div class="text-center">
                <p>Not a member? <a href="register.jsp">Register</a></p>
            </div>
        </form>
    </div>

    <%
        if (request.getAttribute("success") != null) {
    %>
    <div class="alert alert-success">
        <p>Successfully registered, you can login now.</p>
    </div>
    <% } %>

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