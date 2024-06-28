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

            <div class="row mb-3">
                <div class="col-md-6">
                    <div class="form-floating">
                        <input type="number" id="budget" name="budget" class="form-control" placeholder="Enter your budget">
                        <label for="budget">Budget</label>
                    </div>
                </div>
                <div class="col-md-6 d-flex align-items-center justify-content-center">
                    <div class="form-check">
                        <input type="checkbox" id="isAdmin" name="isAdmin" class="form-check-input">
                        <label class="form-check-label" for="isAdmin">Make administrator</label>
                    </div>
                </div>
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

    <script src="js/bootstrap.bundle.js"></script>
</body>
</html>
