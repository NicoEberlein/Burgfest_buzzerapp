window.onload = function() {
    window.setInterval(sendBuzzerDataRequest, 500);
}

function sendBuzzerDataRequest() {
    sendRequest(processBuzzerData, window.location.origin + "/rest/data", "GET", null, new Headers());
    sendRequest(processTeamNames, window.location.origin + "/rest/getTeamNames", "GET", null, new Headers());
}

function processBuzzerData(data) {
    if(data.gameModeType === "Counter") {
		document.getElementById("counter_0").innerHTML = data.buzzerCounter[0];
        document.getElementById("counter_1").innerHTML = data.buzzerCounter[1];
        document.getElementById("counter_2").innerHTML = data.buzzerCounter[2];
	}else if(data.gameModeType === "WhoWasFirst") {
		document.getElementById("counter_0").innerHTML = "";
        document.getElementById("counter_1").innerHTML = "";
        document.getElementById("counter_2").innerHTML = "";
	}
}

function processTeamNames(data) {
	for(var i = 1; i<data.length+1; i++) {
		document.getElementById("teamname" + i).innerHTML = data[i-1];
	}
}

function sendRequest(functionToCall, url, type, body, headers){
    let request = {
        method: type,
        headers: headers,
        body: body
    };
    fetch(url, request)
        .then(response => { return response.json() })
        .then(data => {
            if(functionToCall != null) {
                functionToCall(data);
            }
        });
}
