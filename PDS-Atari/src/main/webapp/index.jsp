<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    boolean loggedIn = session.getAttribute("userBean") != null;
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
<body class="bg-light">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark w-100 mb-2">
        <div class="container">
            <a class="navbar-brand" href="#">PDS-Atari</a>

            <div class="d-flex">
                <span class="flex-grow-1"></span>
                <ul class="navbar-nav ml-auto">
                    <% if (loggedIn) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Cart</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Chat Room</a>
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

    <div id="games-container" class="d-flex flex-wrap gap-2 justify-content-center align-items-center">

    </div>

    <footer class="text-white text-center w-100 bg-dark d-flex align-items-center justify-content-center mt-3">
        <p class="d-flex align-items-center justify-content-center">&copy; 2024 PDS-Atari</p>
    </footer>

    <script src="js/bootstrap.bundle.js"></script>
    <script src="js/scripts.js"></script>
</body>
</html>
