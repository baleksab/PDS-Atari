let ws;
let userId;

function connect() {
    ws = new WebSocket('ws://localhost:8080/PDS_Atari_war_exploded/chat/' + userId);

    ws.onopen = function(event) {
        console.log('WebSocket connected.');
    };

    ws.onmessage = function(event) {
        const message = JSON.parse(event.data);
        displayMessage(message);
    };

    ws.onclose = function(event) {
        console.log('WebSocket closed.');
    };

    ws.onerror = function(error) {
        console.error('WebSocket Error: ' + error);
    };
}

function sendMessage() {
    const messageInput = document.getElementById('messageInput');
    const message = messageInput.value.trim();

    if (message !== '') {
        ws.send(message, userId);
        messageInput.value = '';
    }
}

function displayMessage(message) {
    const chatMessages = document.getElementById('chatMessages');
    const messageElement = document.createElement('div');
    messageElement.classList.add('message', message.userBean.id === userId ? 'sent' : 'received');

    messageElement.innerHTML = `
        <div class="message-header">
            <span class="sender-name">${message.userBean.firstName} ${message.userBean.lastName}</span>
            <span class="timestamp">${message.date}</span>
        </div>
        <div class="message-body">
            ${message.message}
        </div>
    `;
    chatMessages.appendChild(messageElement);
    chatMessages.scrollTop = chatMessages.scrollHeight;
}

function loadChatHistory() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `chat`, false);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            const messages = JSON.parse(xhr.responseText);

            messages.forEach(message => {
                displayMessage(message);
            });
        }
    };

    xhr.send();
}

function load(id) {
    userId = id;
    loadChatHistory();
    connect(userId);
}


document.addEventListener('DOMContentLoaded', (event) => {
    document.getElementById('messageInput').addEventListener('keydown', function (e) {
        if (e.key === 'Enter') {
            sendMessage();
            e.preventDefault();
        }
    });
});
