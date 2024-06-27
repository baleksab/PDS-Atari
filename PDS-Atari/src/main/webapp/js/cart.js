let userId;
let cart;
let budget;
let cost = 0;

function load(id, _budget) {
    userId = id;
    budget = _budget;
    loadCartGames();
}

function loadCartGames() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `cart?userId=${userId}`, false);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            const games = JSON.parse(xhr.responseText);
            cart = games;

            const gamesDiv = document.querySelector('#cart-games');
            gamesDiv.innerHTML = '';


            games.forEach(function (game) {
                let buttonText = "Remove from cart";
                let buttonColor = "btn-danger";
                let buttonFunction = `removeFromCart(${game.id})`;
                let buttonId = `remove${game.id}`;

                let controls = `
                        <div class="d-flex align-items-center justify-content-center gap-1">
                            <span class="flex-grow-1"></span>
                            <button id="${buttonId}" class="btn ${buttonColor}" onclick="${buttonFunction}">${buttonText}</button>
                        </div>
                    `;

                const gameCard = `
                    <div class="game-card mb-1" id="game-card-${game.id}">
                        <img src="${game.path}" alt="${game.name}">
                        <div class="game-details">
                            <h5>${game.name}</h5>
                            <div class="game-meta">
                                <span class="rating">⭐ ${game.rating}</span>
                                <span>💵 $${game.price}</span>
                            </div>
                            <p>${game.description}</p>
                            ${controls}
                        </div>
                    </div>
                `;
                gamesDiv.insertAdjacentHTML('beforeend', gameCard);
            });

            populateCartInformation();
        }
    };

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

function removeFromCart(id) {
    const button = document.querySelector(`#remove${id}`);
    button.disabled = true;

    let xhr = new XMLHttpRequest();
    xhr.open("POST", `removeFromCart?userId=${userId}&gameId=${id}`, true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            const response = JSON.parse(this.responseText);

            if (response.success === true) {
                cart = cart.filter(game => game.id !== id);

                const gameCard = document.querySelector(`#game-card-${id}`);
                if (gameCard) {
                    gameCard.remove();
                }

                populateCartInformation();
            }

            button.disabled = false;
        }
    }

    xhr.send();
}

function populateCartInformation() {
    const purchaseButton = document.querySelector('#purchase');
    const cartInfo = document.querySelector('#cart-information');

    if (cart.length === 0) {
        cartInfo.innerHTML = `<div class="card-text text-danger">Your cart is empty!</div>`;

        purchaseButton.disabled = true;

        return;
    }

    cost = 0;

    cartInfo.innerHTML = `
                <div class="card-text">Your current budget is: $${budget}</div>
                <div class="card-text">You wish to purchase the following games: </div>
            `;

    cart.forEach(function (game)  {
        cartInfo.innerHTML += `
                    <div class="card-text">&nbsp;&nbsp;&nbsp;&nbsp;<span><i>${game.name}</i></span> which costs $${game.price}</div>
                `;

        cost += game.price;
    });

    cartInfo.innerHTML += `
                <div class="card-text">The total is $${cost}</div>
            `;

    if (budget < cost) {
        cartInfo.innerHTML += `
                    <div class="card-text text-danger">You do not have enough money, consider removing some games!</div>
                `;

        purchaseButton.disabled = true;
    } else {
        cartInfo.innerHTML += `
                    <div class="card-text text-success">You have enough money!</div>
                    <div class="card-text">Your new balance would be $${budget - cost}</div>
                `;
        purchaseButton.disabled = false;
    }
}

function buyCartItems() {
    let buyButton = document.querySelector("#purchase")
    buyButton.disabled = true

    let xhr = new XMLHttpRequest()
    xhr.open("POST", `cart?userId=${userId}`, true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            const response = JSON.parse(this.responseText)

            if (response.success === true) {
                alert('successful purchase!');

                cart.forEach(function(game) {
                   budget -= game.price;

                    const gameCard = document.querySelector(`#game-card-${game.id}`);
                    if (gameCard) {
                        gameCard.remove();
                    }
                });

                cart.length = 0;

                const budgetLabel = document.querySelector('#budget');
                budgetLabel.innerHTML = `Budget: $${budget}`;

                populateCartInformation();
            } else {
                alert('failed purchase!');
            }

            buyButton.disabled = false
        }
    }

    xhr.send();
}