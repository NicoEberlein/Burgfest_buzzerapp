window.onload = function() {
	window.setInterval(sendCurrentScoreRequest, 1000);
}

function sendRequest(functionToCall, url, type, body, headers){
	let request = {
		method: type,
		headers: headers,
		body: body
	};
	let responseObj = {
		responseBody: null,
		responseStatus: null
	}

	fetch(url, request)
		.then(function(response) {
			if(!response.ok) {
				throw Error(response.status);
			}
			responseObj.responseStatus = response.status;
			return response.text();
		})
		.then(function(text) {
			if(text.length > 0) {
				return JSON.parse(text);
			}else{
				return null;
			}
		})
		.then(function(response){
			responseObj.responseBody = response;
			if(functionToCall != null) {
				functionToCall(responseObj);	
			}
		})
		.catch(function(error) {
			console.log(error.message);
		});
}

function sendCurrentScoreRequest() {
	sendRequest(processCurrentScoreResponse, window.location.origin + "/rest/data", "GET", null, new Headers());
}

function processCurrentScoreResponse(data) {
	var responseBody = data.responseBody;
	
	if(responseBody.gameModeType === "Counter") {
		
		for(var i = 0;i<3;i++) {
			document.getElementById("current-score-" + (i+1)).innerHTML = responseBody.buzzerCounter[i];
		}
		
	}else if(responseBody.gameModeType === "WhoWasFirst") {
		
		for(var i = 0; i<3; i++) {
			document.getElementById("current-score-" + (i+1)).innerHTML = "";
		}
		
		if(responseBody.firstBuzzer != -1) {
			document.getElementById("current-score-" + (responseBody.firstBuzzer + 1)).innerHTML = "Erster";
		}
		
	}
}

function resetGameData() {
	clearErrors();
	sendRequest(showStatusMessage, window.location.origin + "/rest/reset", "GET", null, new Headers());
}

function incrementBuzzer(buzzer) {
	clearErrors();
	sendRequest(showStatusMessage, window.location.origin + "/rest/incrementBuzzer/" + buzzer, "GET", null, new Headers());
}

function decrementBuzzer(buzzer) {
	clearErrors();
	sendRequest(showStatusMessage, window.location.origin + "/rest/decrementBuzzer/" + buzzer, "GET", null, new Headers());
}




function showStatusMessage(responseObj) {
	if(responseObj.responseStatus == 200) {
		let success_div = document.createElement("div");
		success_div.setAttribute("class", "alert alert-success mt-1");
		success_div.innerHTML = "Daten wurden erfolgreich versendet";

		document.getElementById("status").appendChild(success_div);
	}else{
		let error_div = document.createElement("div");
		success_div.setAttribute("class", "alert alert-danger mt-1");
		success_div.innerHTML = "Es ist ein Fehler aufgetreten. Mehr Details sind in der Konsole zu finden.";

		document.getElementById("status").appendChild(error_div);
	}
}


function clearErrors() {
	let errors = document.getElementsByClassName("alert");
	for(var i=0;i<errors.length;i++) {
		document.getElementById("status").removeChild(errors[i]);
	}
}

function sendFormData() {
	
	clearErrors();
	
	var body = new FormData(document.getElementById("buzzer-settings-form"));
	
	var keysToDelete = [];
	var i = 0;
	
	//Delete empty string parameters
	for(var entry of body) {
		if(entry[1].length == 0) {
			keysToDelete[i] = entry[0];
		}
		i++;
	}
	
	for(var j = 0; j<keysToDelete.length; j++) {
		body.delete(keysToDelete[j]);
	}
	
	console.log(body);
	
	sendRequest(showStatusMessage, window.location.origin + "/rest/settings", "POST", body, new Headers());
	
}







