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

function resetGameData() {
	sendRequest(showStatusMessage, window.location.origin + "/rest/reset", "GET", null, new Headers());
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
	
	if(body.get("teamname1").length == 0) {
		body.set("teamname1", "Team 1");
	}
	if(body.get("teamname2").length == 0) {
		body.set("teamname2", "Team 2");
	}
	if(body.get("teamname3").length == 0) {
		body.set("teamname3", "Team 3");
	}
	
	sendRequest(showStatusMessage, window.location.origin + "/rest/settings", "POST", body, new Headers());
	
}







