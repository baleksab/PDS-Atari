<%@ page import="baleksab.pdsatari.bean.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: c425
  Date: 27.6.2024.
  Time: 19:28
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

    <title>PDS-Atari Cart</title>
</head>
<body class="bg-light" onload="load(<%= userBean.getId() %>, <%= userBean.getBudget() %>)">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark w-100 position-fixed" style="z-index: 100;">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">PDS-Atari</a>

            <div class="d-flex">
                <span class="flex-grow-1"></span>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="cart.jsp">Cart</a>
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

    <div style="min-height: 56px; height: 56px;" class="w-100"></div>

    <div class="d-flex w-100 p-2 align-items-start justify-content-center gap-2" style="height: calc(100% - 56px);">
        <div class="d-flex flex-column gap-2 flex-grow-1 h-100" style="flex-basis: 50%;">
            <div class="card flex-grow-1">
                <div class="card-header">
                    <div class="card-title">Your information</div>
                </div>

                <div class="card-body">
                    <div class="card-text">First Name: <%= userBean.getFirstName() %> </div>
                    <div class="card-text">Last Name: <%= userBean.getLastName() %> </div>
                    <div class="card-text">Email: <%= userBean.getEmail() %> </div>
                    <div class="card-text" id="budget">Budget: $<%= userBean.getBudget() %></div>
                </div>
            </div>

            <div class="card flex-grow-1 overflow-auto">
                <div class="card-header">
                    <div class="card-title">Cart information</div>
                </div>

                <div class="card-body" id="cart-information">

                </div>

                <div class="card-footer d-flex">
                    <span class="flex-grow-1"></span>
                    <button class="btn btn-success" id="purchase" onclick="buyCartItems()">Purchase</button>
                </div>
            </div>
        </div>

        <div class="d-flex flex-column justify-content-start gap-2 flex-grow-1 h-100">
            <div class="form-floating">
                <input type="text" id="searchInput" name="searchInput" class="form-control" placeholder="Search for game..." oninput="search()">
                <label for="searchInput">Search cart...</label>
            </div>

            <div id="cart-games" class="flex-grow-1 overflow-auto">

            </div>
        </div>
    </div>

    <script src="js/bootstrap.bundle.js"></script>
    <script src="js/cart.js"></script>
</body>
</html>
