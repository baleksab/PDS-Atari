<%@ page import="baleksab.pdsbattleship.bean.PlayerBean" %><%--
  Created by IntelliJ IDEA.
  User: c425
  Date: 25.6.2024.
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if (session.getAttribute("playerBean") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    PlayerBean playerBean = (PlayerBean) session.getAttribute("playerBean");
%>

<html>
<head>
    <title>PDS-Battleship</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <h1>PDS-Battleship</h1>
    <h3 class="mb-5">
        Welcome, <%= playerBean.getUsername() %>
        <% if (playerBean.isAdmin()) { %>
            <span class="text-danger"> (Administrator) </span>
        <% } %>
    </h3>

    <div class="d-flex flex-column form-container">
        <button class="btn btn-primary btn-lg menu-button w-100">Find Match</button>
        <button href="#" class="btn btn-primary btn-lg menu-button w-100">Leaderboard</button>
        <button href="#" class="btn btn-primary btn-lg menu-button w-100">Match History</button>
        <% if (playerBean.isAdmin()) { %>
        <button href="#" class="btn btn-primary btn-lg menu-button w-100">Admin Menu</button>
        <% } else { %>
        <button href="#" class="btn btn-secondary btn-lg menu-button w-100" disabled>Admin Menu</button>
        <% } %>
        <form method="post" action="logout">
            <button href="#" class="btn btn-danger btn-lg menu-button w-100 mt-5" type="submit">Logout</button>
        </form>
    </div>

    <script src="js/bootstrap.bundle.js"></script>
</body>
</html>
