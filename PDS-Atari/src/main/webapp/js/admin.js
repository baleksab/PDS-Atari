let userId;
let users = [];
let usersPerPage = 10;

function load(id) {
    userId = id;
    loadUsers();
}

function loadUsers() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `users`, false);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            users = JSON.parse(xhr.responseText)
            setupPagination(users.length);
            loadPage(1);
        }
    };

    xhr.send();
}

function setupPagination(totalUsers) {
    const totalPages = Math.ceil(totalUsers / usersPerPage);
    $('#page-selection').bootpag({
        total: totalPages,
        page: 1,
        maxVisible: 10
    }).on('page', function (event, num) {
        loadPage(num);
    });
}

function loadPage(pageNum) {
    const startIndex = (pageNum - 1) * usersPerPage;
    const endIndex = Math.min(startIndex + usersPerPage, users.length);
    const usersForPage = users.slice(startIndex, endIndex);

    populateTable(usersForPage);
}

function populateTable(users) {
    const tableBody = document.getElementById('userTableBody');
    tableBody.innerHTML = '';

    users.forEach(user => {
        const row = document.createElement('tr');
        row.id = `user${user.id}`;
        row.classList.add(`user-row`);
        row.innerHTML = `
            <td>${user.id}</td>
            <td><input type="email" class="form-control" value="${user.email}" id="email-${user.id}"></td>
            <td><input type="text" class="form-control" value="${user.firstName}" id="firstName-${user.id}" minlength="3"></td>
            <td><input type="text" class="form-control" value="${user.lastName}" id="lastName-${user.id}" minlength="3"></td>
            <td>
                <select class="form-control" id="isAdmin-${user.id}" ${userId === user.id ? 'disabled' : ''}>
                    <option value="true" ${user.isAdmin ? 'selected' : ''}>Yes</option>
                    <option value="false" ${user.isAdmin ? '' : 'selected'}>No</option>
                </select>
            </td>
            <td><input type="number" class="form-control" value="${user.budget}" id="budget-${user.id}" min="0"></td>
            <td><button id="save${user.id}" class="btn btn-success" onclick="saveUser(${user.id})">Save</button></td>
        `;

        tableBody.appendChild(row);
    });
}

function saveUser(id) {
    const saveButton = document.querySelector(`#save${id}`);
    saveButton.disabled = true;

    const email = document.getElementById(`email-${id}`).value;
    const firstName = document.getElementById(`firstName-${id}`).value.trim();
    const lastName = document.getElementById(`lastName-${id}`).value.trim();
    const admin = document.getElementById(`isAdmin-${id}`).value;
    const budget = document.getElementById(`budget-${id}`).value;

    let xhr = new XMLHttpRequest();
    xhr.open('POST', `users?id=${id}&email=${encodeURIComponent(email)}&firstName=${encodeURIComponent(firstName)}&lastName=${encodeURIComponent(lastName)}&isAdmin=${encodeURIComponent(admin)}&budget=${encodeURIComponent(budget)}`, true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            const response = JSON.parse(xhr.responseText);

            if (response.success === true) {
                alert('Successfully updated the user!');

                document.getElementById(`email-${id}`).value = email;
                document.getElementById(`firstName-${id}`).value = firstName;
                document.getElementById(`lastName-${id}`).value = lastName;
                document.getElementById(`isAdmin-${id}`).value = admin;
                document.getElementById(`budget-${id}`).value = budget;

                const index = users.findIndex(user => user.id === id);
                if (index !== -1) {
                    users[index].email = email;
                    users[index].firstName = firstName;
                    users[index].lastName = lastName;
                    users[index].isAdmin = admin;
                    users[index].budget = budget;
                }
            } else {
                alert('Failed to update the user!');

                const index = users.findIndex(user => user.id === id);
                if (index !== -1) {
                    document.getElementById(`email-${id}`).value = users[index].email;
                    document.getElementById(`firstName-${id}`).value = users[index].firstName;
                    document.getElementById(`lastName-${id}`).value = users[index].lastName;
                    document.getElementById(`isAdmin-${id}`).value = users[index].isAdmin;
                    document.getElementById(`budget-${id}`).value = users[index].budget;
                }
            }
        }

        saveButton.disabled = false;
    }

    xhr.send();
}