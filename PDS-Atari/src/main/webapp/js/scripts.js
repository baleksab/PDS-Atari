const API = 'PDS_Atari_war_exploded';

function loadGames() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `games`, true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            const games = JSON.parse(xhr.responseText);

            const tag = document.querySelector('#games-container');
            tag.innerHTML = '';

            games.forEach(function(game) {
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
                tag.insertAdjacentHTML('beforeend', gameCard);
            });
        }
    }

    xhr.send();
}

loadGames();