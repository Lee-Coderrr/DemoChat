var stompClient = null;
var input = document.querySelector('#msgerInput');
var button = document.querySelector('.msger-send-btn');

function updateButtonStatus() {
    if (input.value.trim() === '') {
        button.disabled = true;
    } else {
        button.disabled = false;
    }
}

// 페이지 로드 시 버튼 상태 업데이트
updateButtonStatus();


function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function (chatMessage) {
            showMessage(JSON.parse(chatMessage.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendMessage() {
    var messageInput = document.querySelector('#msgerInput');
    var user_id = document.querySelector('#user_id').value;
    var username = document.querySelector('#username').value;
    
    // 현재 날짜와 시간을 가져옴
    var now = new Date();
    // 날짜와 시간을 'YYYY-MM-DD HH:MM' 형식의 문자열로 변환
    var timestamp = now.getFullYear() + '-' +
        ("0" + (now.getMonth() + 1)).slice(-2) + '-' +
        ("0" + now.getDate()).slice(-2) + ' ' +
        ("0" + now.getHours()).slice(-2) + ':' +
        ("0" + now.getMinutes()).slice(-2);

    stompClient.send("/app/chat", {}, JSON.stringify({
        'username': username,
        'user_id': user_id,
        'text': messageInput.value,
        'timestamp': timestamp   // 'timestamp' 추가
    }));

    messageInput.value = '';
}


function showMessage(message) {
    var msgElement = document.createElement('div');
    msgElement.className = message.username === document.querySelector('#username').value ? 'msg right-msg' : 'msg left-msg';

    var msgImg = document.createElement('div');
    msgImg.className = 'msg-img';
    msgImg.style.backgroundImage = 'url(/images/profile.svg)';

    var msgBubble = document.createElement('div');
    msgBubble.className = 'msg-bubble';

    var msgInfo = document.createElement('div');
    msgInfo.className = 'msg-info';

    var msgInfoName = document.createElement('div');
    msgInfoName.className = 'msg-info-name';
    msgInfoName.innerText = message.username;
    msgInfo.appendChild(msgInfoName);

    // timestamp 표시 추가
    var msgInfoTime = document.createElement('div');
    msgInfoTime.className = 'msg-info-time';
    msgInfoTime.innerText = message.timestamp;
    msgInfo.appendChild(msgInfoTime);

    var msgText = document.createElement('div');
    msgText.className = 'msg-text';
    msgText.innerText = message.text;
    msgBubble.appendChild(msgInfo);
    msgBubble.appendChild(msgText);
    
    msgElement.appendChild(msgImg);
    msgElement.appendChild(msgBubble);



    document.querySelector('#msgerChat').appendChild(msgElement);
    document.querySelector('#msgerChat').scrollTop = document.querySelector('#msgerChat').scrollHeight;
}

document.querySelector('#msgerForm').addEventListener('submit', function (e) {
    e.preventDefault();
    if (document.querySelector('#msgerInput').value.trim() !== '') {
        sendMessage();
    }
    document.querySelector('#msgerInput').focus();
    console.log("버튼 클릭!")
});

function formatDate(date) {
    const year = date.getFullYear();
    const month = "0" + (date.getMonth() + 1); // JavaScript는 0부터 11까지의 숫자를 이용해 월을 표현합니다.
    const day = "0" + date.getDate();
    const hour = "0" + date.getHours();
    const min = "0" + date.getMinutes();

    return `${year}-${month.slice(-2)}-${day.slice(-2)} ${hour.slice(-2)}:${min.slice(-2)}`;
}

// 'keyup'과 'input' 이벤트 핸들러 추가
input.addEventListener('keyup', function(event) {
    updateButtonStatus();

    if (event.keyCode === 13 && input.value.trim() !== '') {
        event.preventDefault();
        sendMessage();
    }
});

input.addEventListener('input', updateButtonStatus);


connect();



