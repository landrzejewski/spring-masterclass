let stompClient = null;

function setConnected(connected) {
    $('#username').prop('disabled', connected);
    $('#connectBtn').prop('disabled', connected);
    $('#disconnectBtn').prop('disabled', !connected);
    $('#response').innerHTML = '';
}

function connect() {
    const socket = new SockJS('/shop-0.0.1-SNAPSHOT/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        stompClient.subscribe('/topic/messages', messageOutput => showMessageOutput(JSON.parse(messageOutput.body)));
    });
}

function disconnect() {
    if (stompClient) {
        stompClient.disconnect();
    }
    setConnected(false);
}

function sendMessage() {
    const message = JSON.stringify({'from': $('#username').val(), 'text': $('#message').val()});
    stompClient.send("/app/chat", {}, message);
}

function showMessageOutput(messageOutput) {
    $('<p>' + messageOutput.from + ': ' + messageOutput.text + '</p>').appendTo($('#response'));
}

$(() =>{
    $('#connectBtn').click(connect)
    $('#disconnectBtn').click(disconnect)
    $('#sendBtn').click(sendMessage)
})
