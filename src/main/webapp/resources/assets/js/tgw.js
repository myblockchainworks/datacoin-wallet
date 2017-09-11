if (!Date.prototype.toLocalISOString) {
    // Anonymous self-invoking function
    (function () {

        function pad(number) {
            if (number < 10) {
                return '0' + number;
            }
            return number;
        }

        Date.prototype.toLocalISOString = function () {
            timestamp = this.getFullYear() +
              '-' + pad(this.getMonth() + 1) +
              '-' + pad(this.getDate()) +
              'T' + pad(this.getHours()) +
              ':' + pad(this.getMinutes()) +
              ':' + pad(this.getSeconds());
            if (this.getTimezoneOffset() == 0) { timestamp = timestamp + "Z" }
            else {
                if (this.getTimezoneOffset() < 0) { timestamp = timestamp + "+" }
                else { timestamp = timestamp + "-" }
                timestamp = timestamp + pad(Math.abs(this.getTimezoneOffset() / 60).toFixed(0));
                timestamp = timestamp + ":" + pad(Math.abs(this.getTimezoneOffset() % 60).toFixed(0));
            }
            return timestamp;
        };

    }());
}

var downloadSessionData = "id,unitsForPrice,unitPrice,deviceId,sessionType,isStop,limit,totalSessionCost,totalDataConsumed,totalSessionTime,startedAt,stoppedAt,buyer,seller\n";
function formatAndDisplayDate(value, className) {
	var date = new Date(value);
	var monthNames = [
	  "Jan", "Feb", "Mar",
	  "Apr", "May", "Jun", "Jul",
	  "Aug", "Sep", "Oct",
	  "Nov", "Dec"
	];
	
	var day = date.getDate();
	var monthIndex = date.getMonth();
	var year = date.getFullYear();
	
	var hour = date.getHours()
	var min = date.getMinutes();
	if (hour < 10) {
		hour = '0' + hour;
	} 
	
	if (min < 10) {
		min = '0' + min;
	}
	
	var formatted = day + '-' + monthNames[monthIndex] + '-' + year + ' ' + hour + ':' + min;
	$('.' + className).html(formatted); 
}


//Calculate the difference of two dates in total days
function diffDays(d1, d2)
{
  var ndays;
  var tv1 = d1.valueOf();  // msec since 1970
  var tv2 = d2.valueOf();

  ndays = (tv2 - tv1) / 1000 / 86400;
  ndays = Math.round(ndays - 0.5);
  return ndays;
}


function showErrorMessage(message) {
	if (message != "") {
		$(".errorMessageDiv").html('<div id="login-alert" class="alert alert-danger col-sm-12">' + message + '</div>');
		setTimeout(function() { 
			//window.location.href ="ownerhome";
			$(".errorMessageDiv").fadeOut(1000);
		}, 3000);
	}
}

function showSuccessMessage(message) {
	if (message != "") {
		$(".successMessageDiv").html('<div id="login-alert" class="alert alert-success col-sm-12">' + message + '</div>');
		setTimeout(function() { 
			//window.location.href ="ownerhome";
			$(".successMessageDiv").fadeOut(1000);
		}, 3000);
	}
}

function formatAddress(address, className, currentAddress) {
	var formatted = address;
	if (address == currentAddress) {
		formatted = '<span style="font-weight: bold;color: #087480;">' + address + '</span>'
	}
	$('.' + className).html(formatted); 
}

function showAmount(amount, type, className) {
	var formatted = amount + ' ETH';
	if (type == 'Token Transaction') {
		formatted = amount + ' WIFI';
	}
	$('.' + className).html(formatted); 
}

function downloadKeyStore() {
	var privatekey = $('#privatekey').html();
	var downloadData = $('#keystoreJson').val();
	downloadData = downloadData.replace(/\\/g, '\"');
	var hiddenElement = document.createElement('a');
	document.body.appendChild(hiddenElement);
	hiddenElement.setAttribute("type", "hidden"); // make it hidden if needed
	hiddenElement.href = 'data:text/json;charset=utf-8,' + encodeURI(downloadData);
	hiddenElement.target = '_blank';
	hiddenElement.download = 'keystore-' + privatekey + '.json';
	hiddenElement.click();
}

function buyTokenConfirmation() {
	var ether = $('#etheramount').val();
	if (ether != undefined && ether != '') {
		if(confirm ("Are you sure you want to buy Token for " + ether + " Ether?")) {
			$('#buyTokenForm').submit();
		}
	} else {
		alert ("Please enter the ether amount to buy Token!");
	}
}

function sendTokenConfirmation() {
	var toaddress = $('#toaddress').val();
	var tokenamount = $('#tokenamount').val();
	if (toaddress != undefined && toaddress != '' && tokenamount != undefined && tokenamount != '') {
		if (tokenamount % 1 != 0) {
			alert ("Please enter a valid Token amount!");
		} else {
			if(confirm ("Are you sure you want to send " + tokenamount + " Token to " + toaddress + "?")) {
				$('#sendTokenForm').submit();
			}
		}
		
	} else {
		alert ("Please fill the recipient address and Token amount!");
	}
}

function updateSessionDetail(id, unitsForPrice, unitPrice, deviceId, sessionType, isStop, limit, totalSessionCost, totalDataConsumed, totalSessionTime, startedAt, stoppedAt, buyer, seller) {
	downloadSessionData += id + "," + unitsForPrice + "," + unitPrice + "," + deviceId + "," + sessionType + "," + isStop + "," + limit + "," + totalSessionCost + "," + totalDataConsumed + "," + totalSessionTime + "," + startedAt + "," + stoppedAt + "," + buyer + "," + seller + "\n";
}	

function sessionDataDownloadOption() {
	var now = new Date().toLocalISOString().slice(0,19);
	downloadSessionData = downloadSessionData.replace(/\\/g, '\"');
	var hiddenElement = document.createElement('a');
	document.body.appendChild(hiddenElement);
	hiddenElement.setAttribute("type", "hidden"); // make it hidden if needed
	hiddenElement.href = 'data:text/json;charset=utf-8,' + encodeURI(downloadSessionData);
	hiddenElement.target = '_blank';
	hiddenElement.download = 'SessionData_'+now+'.csv';
	hiddenElement.click();
}