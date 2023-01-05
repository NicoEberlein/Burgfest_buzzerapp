window.onload = function() {
    window.setInterval(sendBuzzerDataRequest, 500);
}

function sendBuzzerDataRequest() {
    sendRequest(processBuzzerData, window.location.origin + "/rest/data", "GET", null, new Headers());
    sendRequest(processTeamNames, window.location.origin + "/rest/getTeamNames", "GET", null, new Headers());
}

function processBuzzerData(data) {
	
	document.getElementById("multipurposeframe_0").setAttribute("class", data.gameModeType.toLowerCase());
	document.getElementById("multipurposeframe_1").setAttribute("class", data.gameModeType.toLowerCase());
	document.getElementById("multipurposeframe_2").setAttribute("class", data.gameModeType.toLowerCase());
	
    if(data.gameModeType === "Counter") {
	
		document.getElementById("multipurposeframe_0").innerHTML = data.buzzerCounter[0];
        document.getElementById("multipurposeframe_1").innerHTML = data.buzzerCounter[1];
        document.getElementById("multipurposeframe_2").innerHTML = data.buzzerCounter[2];
        
	}
}

function processTeamNames(data) {
	for(var i = 0; i<data.length; i++) {
		document.getElementById("teamname" + i).innerHTML = data[i];
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
