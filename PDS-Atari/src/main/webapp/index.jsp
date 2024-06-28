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
                        <a class="nav-link" href="inventory.jsp">Inventory</a>
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
                    <% if (isAdmin) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="admin.jsp">Admin</a>
                    </li>
                    <% } %>
                    <li class="nav-item">
                        <button class="nav-link" data-bs-toggle="modal" data-bs-target="#help">Help (?)</button>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div style="min-height: 56px; height: 56px;" class="w-100 mb-3"></div>

    <div style="height: calc(100% - 68px); min-height: calc(100% - 68px)" class="w-100">
        <div class="d-flex gap-3 mb-3 align-items-center justify-content-center">
            <div class="form-floating z-0">
                <input type="text" id="searchInput" name="searchInput" class="form-control" placeholder="Search for game..." oninput="search()">
                <label for="searchInput">Search for game...</label>
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

    <div class="modal fade" id="help" tabindex="-1" role="dialog" aria-labelledby="helpModal" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Uputstvo</h5>
                </div>
                <div class="modal-body">
                    <p><b>PDS - Atari (index.jsp)</b></p>
                    <p>
                        Na ovoj stranici mozete videti sve igrice koje se nalaze u bazi. Postoji pretraga, kao i paginacija. Pretraga se odnosi
                        samo na trenutnu stranicu paginatora. Za svaku igricu se zna njena ocena, cena kao i stanje. Da bi mogli bilo sta da odradimo,
                        moramo se prijaviti. Nakon prijave, u zavisnosti od tipa naloga (da li je admin ili nije), korisnik moze videti dugmice. Ukoliko
                        je korisnik administrator, moze videti 'edit' dugme, preko kojeg moze menjati opis, cenu i stanje proizvoda. Drugo dugme sluzi za
                        dodavanje proizvoda u korpu. U korpi se moze nalaziti samo jedan isti proizvod, ne vise njih. Takodje je nemoguce dodati u korpu
                        u slucaju ako je igrica vec kupljena. Ukoliko je igrica u korpi, moze se skinuti iz korpe klikom na drugo dugme. Moze se primetiti
                        da sklanjanjem i dodavanjem proizvoda u korpu se povecava/smanjuje stanje te igrice.
                    </p>

                    <p><b>Login (login.jsp) i Register (register.jsp)</b></p>

                    <p>
                        Preko ovih stranica moguce je napraviti naloge i prijaviti se na sistem. Prilikom registrovanja, moze se izabrati da li se pravi
                        administratorski nalog kao i koliki je budzet tom nalogu. Obavezno je da ime i prezime budu bar 3 karaktera, a sifra barem 6 karaktera.
                    </p>

                    <p><b>Admin (admin.jsp)</b></p>

                    <p>
                        Ovde administratorski nalozi mogu videti ostale naloge u tabularnom obliku, i menjati njihove informacije. Da bi se promene videle kod
                        klijenata, moraju se ulogovati i izlogovati.
                    </p>

                    <p><b>Chat Room (chat.jsp)</b></p>

                    <p>
                        Ovo je chat preko koga mogu komunicirati svi prijavljeni korisnici. Istorija ceta se pamti, ali administratori imaju mogucnost da je obrisu.
                        Za ovo je potrebno da RMI server bude pokrenut da bi radilo.
                    </p>

                    <p><b>Cart (cart.jsp)</b></p>

                    <p>
                        Na ovoj stranici je moguce da korisnik vidi informacije o njemu, kao i informacije o stanju njegove trenutne korpe. Moze uklanjati igrice iz korpe,
                        takodje moze i da ih kupi i time prebaci u svoj inventar (inventory). Moze videti koliki ce biti njegov trosak, kao i koja igrica koliko kosta.
                    </p>

                    <p><b>Inventory (inventory.jsp)</b></p>

                    <p>
                        Na ovoj stranici korisnik moze videti svoj inventar, i takodje moze prodavati svoje kupljenje igrice po 70% od kupljene cene. Ukoliko se igrica proda,
                        korisniku se vraca 70% cene u budzet, povecava se stanje igrice i korisnik je moze ponovo kupiti.
                    </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
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
