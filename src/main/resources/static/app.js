// // JavaScript WebSocket code here...
//     const socket = new WebSocket('ws://localhost:9133/tags');
//     const chatBox = document.getElementById('chat-box');
//     const messageInput = document.getElementById('message-input');
//     const sendButton = document.getElementById('send-button');

//     socket.onopen = function(event) {
//       console.log('WebSocket connection established');
//     };

//     socket.onmessage = function(event) {
//       const message = event.data;
//       displayMessage('Received message: ' + message);
//       // Handle the received message
//     };

//     socket.onclose = function(event) {
//       console.log('WebSocket connection closed');
//     };

//     // Send a message to the server
//     function sendMessage(message) {
//       socket.send(message);
//     }

//     // Display a message in the chat box
//     function displayMessage(message) {
//       const p = document.createElement('p');
//       p.textContent = message;
//       chatBox.appendChild(p);
//     }

//     sendButton.addEventListener('click', function() {
//       const message = messageInput.value;
//       sendMessage(message);
//       messageInput.value = '';
//     });

// axios.get("ws://localhost:9133/tags")
// .then((response) => console.log(response.data))
// .catch((error) => console.log(error));

// const exampleSocket = new WebSocket("ws://localhost:9133/tags");

// exampleSocket.onmessage = (event) => {
//   console.log(event.data);
// };

var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/tags');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/details', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});