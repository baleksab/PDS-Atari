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
                const gameCard = `
                    <div class="game-card">
                        <img src="${game.path}" alt="${game.name}">
                        <div class="game-details">
                            <h5>${game.name}</h5>
                            <div class="game-meta">
                                <span class="rating">⭐ ${game.rating}</span>
                                <span>💵 $${game.price}</span>
                            </div>
                            <p>${game.description}</p>
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

$('#page-selection').bootpag({
    total: 6,
    page: 1,
    maxVisible: 6
}).on('page', function(event, num){
    loadGames(num);
});

