let userId;
let isAdmin;

function load(id, admin) {
    userId = id;
    isAdmin = admin
    loadGames(1);
}


function loadGames(pageNumber) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `games?page=${pageNumber}&size=10`, false);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            const games = JSON.parse(xhr.responseText);

            const gamesDiv = document.querySelector('#games-container');
            gamesDiv.innerHTML = '';


            games.forEach(function (game) {
                let color = ''
                let emoji = '🟢';
                let noStock = false;

                if (game.stock < 1) {
                    color = 'text-danger';
                    emoji = '🔴';
                    noStock = true;
                }

                let loggedInControls = '';

                if (userId !== -1) {
                    let buttonText = "Add to cart";
                    let buttonColor = "btn-success";
                    let buttonFunction = `addToCart(${game.id})`;
                    let buttonId = `buy${game.id}`;
                    let buttonDisabled = '';

                    if (game.customerCarts.includes(userId)) {
                        buttonText = "Remove from cart";
                        buttonColor = "btn-danger";
                        buttonFunction = `removeFromCart(${game.id})`;
                    } else if (game.customerInventories.includes(userId)) {
                        buttonText = "Already owned";
                        buttonColor = 'btn-dark';
                        buttonDisabled = 'disabled';
                    } else if (noStock) {
                        buttonText = "Out of stock";
                        buttonColor = "btn-dark";
                        buttonDisabled = 'disabled';
                    }

                    if (isAdmin) {
                        loggedInControls = `
                            <div class="d-flex align-items-center justify-content-center gap-1">
                                <span class="flex-grow-1"></span>
                                <button class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editGameModal" onclick="editGame(${game.id})">Edit</button>
                                <button id="${buttonId}" class="btn ${buttonColor}" onclick="${buttonFunction}" ${buttonDisabled}>${buttonText}</button>
                            </div>
                        `;
                    } else {
                        loggedInControls = `
                            <div class="d-flex align-items-center justify-content-center gap-1">
                                <span class="flex-grow-1"></span>
                                <button id="${buttonId}" class="btn ${buttonColor}" onclick="${buttonFunction}" ${buttonDisabled}>${buttonText}</button>
                            </div>
                        `;
                    }
                }

                const gameCard = `
                    <div class="game-card" id="gameCard${game.id}">
                        <img src="${game.path}" alt="${game.name}">
                        <div class="game-details">
                            <h5>${game.name}</h5>
                            <div class="game-meta">
                                <span class="rating">⭐ ${game.rating}</span>
                                <span>💵 $${game.price}</span>
                                <span id="stock${game.id}" data-stock="${game.stock}">${emoji} <span class="${color}">${game.stock}</span></span>
                            </div>
                            <p>${game.description}</p>
                            ${loggedInControls}
                        </div>
                    </div>
                `;
                gamesDiv.insertAdjacentHTML('beforeend', gameCard);
            });

            search();
        }
    };

    xhr.send();
}

function addToCart(id) {
    const button = document.querySelector(`#buy${id}`);
    const stock = document.querySelector(`#stock${id}`);
    button.disabled = true;

    let xhr = new XMLHttpRequest();
    xhr.open("POST", `addToCart?userId=${userId}&gameId=${id}`, true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            const response = JSON.parse(this.responseText);

            if (response.success === true) {
                button.classList.remove('btn', 'btn-success');
                button.classList.add('btn', 'btn-danger');
                button.textContent = "Remove from cart";

                button.onclick = function() {
                    removeFromCart(id);
                }

                stock.dataset.stock = Number(stock.dataset.stock) - 1;

                let color = ''
                let emoji = '🟢';

                if (stock.dataset.stock < 1) {
                    color = 'text-danger';
                    emoji = '🔴';
                }

                stock.innerHTML = `
                    ${emoji} <span class="${color}">${stock.dataset.stock}</span>
                `;
            }

            button.disabled = false;
        }
    }

    xhr.send();
}

function removeFromCart(id) {
    const button = document.querySelector(`#buy${id}`);
    const stock = document.querySelector(`#stock${id}`);
    button.disabled = true;

    let xhr = new XMLHttpRequest();
    xhr.open("POST", `removeFromCart?userId=${userId}&gameId=${id}`, true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            const response = JSON.parse(this.responseText);

            if (response.success === true) {
                button.classList.remove('btn', 'btn-danger');
                button.classList.add('btn', 'btn-success');
                button.textContent = "Add to cart";

                button.onclick = function() {
                    addToCart(id);
                }

                stock.dataset.stock = Number(stock.dataset.stock) + 1;

                let emoji = '🟢';

                stock.innerHTML = `
                    ${emoji} <span>${stock.dataset.stock}</span>
                `;
            }

            button.disabled = false;
        }
    }

    xhr.send();
}

function search() {
    const searchInput = document.getElementById('searchInput');
    const searchTerm = searchInput.value.toLowerCase();
    const gameCards = document.querySelectorAll('.game-card');

    gameCards.forEach(function (card) {
        const gameName = card.querySelector('h5').textContent.toLowerCase();
        if (gameName.includes(searchTerm)) {
            card.style.display = 'flex';
        } else {
            card.style.display = 'none';
        }
    });
}

function editGame(gameId) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `game?gameId=${gameId}`, true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            const game = JSON.parse(xhr.responseText);

            const modalBody = document.getElementById('gameDetailsModalBody');
            modalBody.innerHTML = `
                <h5>${game.name}</h5>
                <div class="form-group">
                    <label for="editDescription">Description:</label>
                    <textarea id="editDescription" class="form-control" minlength="3">${game.description}</textarea>
                </div>
                <div class="form-group">
                    <label for="editPrice">Price:</label>
                    <input type="number" id="editPrice" class="form-control" value="${game.price}" min="1">
                </div>
                <div class="form-group">
                    <label for="editStock">Stock:</label>
                    <input type="number" id="editStock" class="form-control" value="${game.stock}" min="0">
                </div>
            `;

            const button = document.querySelector('#saveChangesBtn');
            button.onclick = function () {
                saveChanges(gameId);
            }
        }
    };

    xhr.send();
}

function saveChanges(gameId) {
    const saveButton = document.querySelector('#saveChangesBtn');
    saveButton.disabled = true;

    const cancelButton = document.querySelector('#cancelChangesBtn');
    cancelButton.disabled = true;

    const description = document.getElementById('editDescription').value;
    const price = document.getElementById('editPrice').value;
    const stock = document.getElementById('editStock').value;

    let xhr = new XMLHttpRequest();
    xhr.open('POST', `game?id=${gameId}&description=${encodeURIComponent(description)}&price=${price}&stock=${stock}`, true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            const response = JSON.parse(xhr.responseText);

            if (response.success === true) {
                alert('Changes saved successfully!');

                const gameDescription = document.querySelector(`#gameCard${gameId} .game-details p`);
                const gamePrice = document.querySelector(`#gameCard${gameId} .game-meta span:nth-child(2)`);
                const gameStock = document.querySelector(`#gameCard${gameId} .game-meta #stock${gameId} span`);

                gameDescription.textContent = description;
                gamePrice.textContent = `💵 $${price}`;
                gameStock.textContent = stock;
            } else {
                alert('Failed to save changes.');
            }

            saveButton.disabled = false;
            cancelButton.disabled = false;
        }
    };

    xhr.send();
}

$('#page-selection').bootpag({
    total: 6,
    page: 1,
    maxVisible: 6
}).on('page', function(event, num){
    loadGames(num);
});

