let userId;
let inventory;
let budget;

function load(id, _budget) {
    userId = id;
    budget = _budget;
    loadInventoryGames();
}

function loadInventoryGames() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `inventory?userId=${userId}`, false);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            inventory = JSON.parse(xhr.responseText);

            const inventoryDiv = document.querySelector('#inventory-games');

            inventory.forEach(game => {
                let buttonText = "Sell";
                let buttonColor = "btn-danger";
                let buttonFunction = `sellFromInventory(${game.id})`;
                let buttonId = `sell${game.id}`;

                let controls = `
                    <div class="d-flex align-items-center justify-content-center gap-2">
                        <h5 class="text-danger">You can sell this game for $${game.price * 0.7}</h5>
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
                inventoryDiv.insertAdjacentHTML('beforeend', gameCard);
            });

            if (inventory.length === 0) {
                const warning = document.querySelector('#empty-inventory');
                warning.textContent = 'You have no games in your inventory!';
            }
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

function sellFromInventory(id) {
    const button = document.querySelector(`#sell${id}`);
    button.disabled = true;

    let xhr = new XMLHttpRequest();
    xhr.open("POST", `inventory?userId=${userId}&gameId=${id}`, true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            const response = JSON.parse(this.responseText);

            if (response.success === true) {
                const removedGame = inventory.find(game => game.id === id);

                inventory = inventory.filter(game => game.id !== id);

                const gameCard = document.querySelector(`#game-card-${id}`);
                if (gameCard) {
                    gameCard.remove();
                }

                budget += (removedGame.price * 0.7);

                const budgetLabel = document.querySelector('#budget');
                budgetLabel.innerHTML = `Budget: $${budget}`;
            }

            button.disabled = false;
        }
    }

    xhr.send();
}
