$( document ).ready(function() {
	if ("WebSocket" in window) {
       console.log("WebSocket is supported by your Browser!");
    } else {
    	console.log("WebSocket NOT supported by your Browser!");
    	return;
    }	
	let getScriptParamUrl = function() {
		let scripts = document.getElementsByTagName('script');
		let lastScript = scripts[scripts.length-1];
	    return lastScript.getAttribute('data-url');
	};

	let send = function() {
		let text = $message.val();
		$message.val("");
		connection.send(text);
	};

	let $messages = $("#messages"), $send = $("#send"), $message = $("#message");

	let url = getScriptParamUrl();
	const connection = new WebSocket(url);

	$send.prop("disabled", true);
		
	connection.onopen = function() {
		$send.prop("disabled", false);
		$messages
				.prepend($("<li class='bg-success p-2' style='font-size: 1.5em'>Connected</li>"));
		$send.on('click', send);
		$message.keypress(function(event) {
			let keycode = (event.keyCode ? event.keyCode : event.which);
			if (keycode == '13') {
				send();
			}
		});
	};
	connection.onerror = function(error) {
		console.log('WebSocket Error ', error);
	};
	connection.onmessage = function(event) {
		$messages.append($("<li class='list-group-item' style='font-size: 1.5em'>" + event.data + "</li>"))
	}

	console.log( "chat app is running!" );	
});