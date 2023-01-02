window.onload = function() {
    window.setInterval(sendBuzzerDataRequest, 500);
}

function sendBuzzerDataRequest() {
    sendRequest(processBuzzerData, window.location.origin + "/data", "GET", null, new Headers());
}

function processBuzzerData(data) {
    if(data != null) {
        document.getElementById("counter_0").innerHTML = data.data[0];
        document.getElementById("counter_1").innerHTML = data.data[1];
        document.getElementById("counter_2").innerHTML = data.data[2];
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
