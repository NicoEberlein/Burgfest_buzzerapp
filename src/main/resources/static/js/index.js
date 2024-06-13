var lastReceivedPressId = 0;
var blocked = false;

window.onload = function() {
    window.setInterval(sendBuzzerDataRequest, 500);
}

function sendBuzzerDataRequest() {
    sendRequest(processBuzzerData, window.location.origin + "/rest/data", "GET", null, new Headers());
    sendRequest(processTeamNames, window.location.origin + "/rest/getTeamNames", "GET", null, new Headers());
}

function processBuzzerData(data) {
	
    document.getElementById("multipurposeframe_0").setAttribute("class", "multipurposeframe_" + data.gameModeType.toLowerCase());
	document.getElementById("multipurposeframe_1").setAttribute("class", "multipurposeframe_" + data.gameModeType.toLowerCase());
	document.getElementById("multipurposeframe_2").setAttribute("class", "multipurposeframe_" + data.gameModeType.toLowerCase());
	
    if(data.gameModeType === "Counter") {
	
        document.getElementById("multipurposeframes").style.display = "block";
        document.getElementById("name-selection").style.display = "none";
		document.body.style.backgroundColor = "darkgrey";
	
		document.getElementById("multipurposeframe_0").innerHTML = data.buzzerCounter[0];
        document.getElementById("multipurposeframe_1").innerHTML = data.buzzerCounter[1];
        document.getElementById("multipurposeframe_2").innerHTML = data.buzzerCounter[2];
        
	}else if(data.gameModeType === "WhoWasFirst") {

        document.getElementById("multipurposeframes").style.display = "block";
        document.getElementById("name-selection").style.display = "none";

		document.getElementById("multipurposeframe_0").innerHTML = "";
        document.getElementById("multipurposeframe_1").innerHTML = "";
        document.getElementById("multipurposeframe_2").innerHTML = "";
        
        if(data.firstBuzzer != -1) {
			document.body.style.backgroundColor = data.teamBackgroundColor;
		}else{
			document.body.style.backgroundColor = "darkgrey";
		}
	
    }else if(data.gameModeType === "NameSelection") {

        document.getElementById("multipurposeframes").style.display = "none";
        document.getElementById("name-selection").style.display = "block";
        document.body.style.backgroundColor = "darkgrey";

        if(data.lastPressId == 0) {
            lastReceivedPressId = 0;
            return;
        }

        if(blocked) { lastReceivedPressId = data.lastPressId; }
    
        if(data.lastPressId > lastReceivedPressId && !blocked) {

            blocked = true;

            lastReceivedPressId = data.lastPressId;
            document.getElementById("name-selection").innerHTML = data.names[getRndInteger(0, data.names.length)];

            window.setTimeout(() => {
                blocked = false;
                document.getElementById("name-selection").innerText = "?";
            }, 5000);
        }

    }
		
}

function processTeamNames(data) {
	for(var i = 0; i<data.length; i++) {
		document.getElementById("teamname" + i).innerHTML = data[i];
	}
}

function getRndInteger(min, max) {
    return Math.floor(Math.random() * (max - min) ) + min;
}

function sendRequest(functionToCall, url, type, body, headers){
    fetch(url, {method: type, headers: headers, body: body} )
        .then(response => { return response.json() })
        .then(data => {
            if(functionToCall != null) {
                functionToCall(data);
            }
        });
}
