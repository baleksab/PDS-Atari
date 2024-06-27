<%@ page import="baleksab.pdsatari.bean.UserBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    boolean loggedIn = session.getAttribute("userBean") != null;
    int id = -1;
    boolean isAdmin = false;

    if (loggedIn) {
        id = ((UserBean) session.getAttribute("userBean")).getId();
        isAdmin = ((UserBean) session.getAttribute("userBean")).isAdmin();
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/styles.css">
    <title>PDS-Atari</title>
</head>
<body class="bg-light" onload="load(<%= id %>, <%= isAdmin %>)">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark w-100 position-fixed" style="z-index: 100;">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">PDS-Atari</a>

            <div class="d-flex">
                <span class="flex-grow-1"></span>
                <ul class="navbar-nav ml-auto">
                    <% if (loggedIn) { %>
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
                    <% } else { %>
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="register.jsp">Register</a>
                    </li>
                    <% } %>
                </ul>
            </div>
        </div>
    </nav>

    <div style="min-height: 56px; height: 56px;" class="w-100 mb-3"></div>

    <div style="height: calc(100% - 68px); min-height: calc(100% - 68px)" class="w-100">
        <div class="d-flex gap-3 mb-3 align-items-center justify-content-center">
            <div class="form-floating z-0">
                <input type="text" id="searchInput" name="searchInput" class="form-control" placeholder="Search for game..." oninput="search()">
                <label for="searchInput">Search for a game...</label>
            </div>

            <div id="page-selection" class="d-flex align-items-center justify-content-center"></div>
        </div>

        <div id="games-container" class="d-flex flex-wrap gap-2 justify-content-center align-items-center pb-3"></div>
    </div>

    <div class="modal fade" id="editGameModal" tabindex="-1" role="dialog" aria-labelledby="editGameModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editGameModalLabel">Edit Game</h5>
                </div>
                <div class="modal-body" id="gameDetailsModalBody">
                </div>
                <div class="modal-footer">
                    <button id="cancelChangesBtn" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button id="saveChangesBtn" class="btn btn-primary">Save Changes</button>
                </div>
            </div>
        </div>
    </div>

    <script src="js/jquery.js"></script>
    <script src="js/bootpag.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
    <script src="js/index.js"></script>
</body>
</html>
