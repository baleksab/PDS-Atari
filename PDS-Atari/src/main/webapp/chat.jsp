<%@ page import="baleksab.pdsatari.bean.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: c425
  Date: 26.6.2024.
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if (session.getAttribute("userBean") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    UserBean userBean = (UserBean) session.getAttribute("userBean");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/styles.css">

    <title>PDS-Atari Chat</title>
</head>
<body class="bg-light" onload="load(<%= userBean.getId() %>)">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark w-100 position-fixed" style="z-index: 100;">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">PDS-Atari Chat</a>

            <div class="d-flex">
                <span class="flex-grow-1"></span>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Cart</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="chat.jsp">Chat Room</a>
                    </li>
                    <li class="nav-item">
                        <form method="post" action="logout">
                            <button class="nav-link" type="submit">Logout</button>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div style="min-height: 56px; height: 56px;" class="w-100 mb-3"></div>

    <div class="container mb-3">
        <div id="chatMessages" class="d-flex flex-column"></div>

        <div class="d-flex gap-1 w-100 align-items-center justify-content-center">
            <div class="form-floating flex-grow-1">
                <input type="text" id="messageInput" name="firstName" class="form-control" placeholder="Type your message..." max="255" min="1">
                <label for="messageInput">Type your message...</label>
            </div>
            <% if (userBean.isAdmin()) { %>
            <form action="rmi-execution.jsp" method="post">
                <button type="submit" class="btn btn-dark">Clear History</button>
            </form>
            <% } %>
        </div>

    </div>

    <script src="js/bootstrap.bundle.js"></script>
    <script src="js/chat.js"></script>
</body>
</html>