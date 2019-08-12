var today = new Date();
var dd = today.getDate();
var mm = today.getMonth() + 1; // January is 0
var yyyy = today.getFullYear();

if (dd < 10) {
	dd = '0' + dd
}

if (mm < 10) {
	mm = '0' + mm
}

today = yyyy + '-' + mm + '-' + dd;



// https://www.htmlgoodies.com/html5/javascript/calculating-the-difference-between-two-dates-in-javascript.html
// ///////////////////////////////////////////////// FUNCTION TO COUNT # OF DAYS
// Date.daysBetween = function(date1, date2) {
// // Get 1 day in milliseconds
// var one_day = 1000 * 60 * 60 * 24;
//
// // Convert both dates to milliseconds
// var date1_ms = date1.getTime();
// var date2_ms = date2.getTime();
//
// // Calculate the difference in milliseconds
// var difference_ms = date2_ms - date1_ms;
//
// // Convert back to days and return
// return Math.round(difference_ms / one_day);
// }
//
// // Set the two dates
// var y2k = new Date(2000, 0, 1);
// var Jan1st2010 = new Date(y2k.getFullYear() + 10, y2k.getMonth(),
// y2k.getDate());
// var today = new Date();
// // displays 726
// console.log('Days since ' + Jan1st2010.toLocaleDateString() + ': '
// + Date.daysBetween(Jan1st2010, today));
window.onload = function() {
document.getElementById("cost").addEventListener("change", alerter);
document.getElementById("typeOfEvent").addEventListener("change", alerter);
//document.getElementById("formSubmit").addEventListener("click", postForm,
//		false);
//document.getElementById("currentDate").value = today;
}


/*function postForm(){
	alert("in post form");

	//let vg = document.getElementById("vgForm").submit;
	var xhr2 = new XMLHttpRequest();
	xhr2.onreadystatechange = function() {
		console.log("in post form on ready change");
		console.log("in ORSC " + xhr2.readyState + xhr2.status);
		if (xhr2.readyState == 4 && xhr2.status == 200) {
			console.log(xhr.responseText);
		}
	}
	xhr2.open("POST","http://localhost:8080/TRMS/form",false);
	var trash=jsonBuilder();
	alert(trash);
	xhr2.send(trash); //Wtf, trash is not escapecharactered and also is escapecharactered? Also why is this json stuff so reeeeeeeeeeee
}

function jsonBuiilder() {
	var elements = document.getElementById("form").elements;
	//var elements = document.getElementsByClassName("form-control");
	var obj ={};
	for(var i = 0 ; i < elements.length; i++){
		var item = elements.item(i);
		console.log(item.tagName + " is type");
		if((item.tagName == "INPUT" || item.tagName == "SELECT") && item.id != "reimbursementamount"){
        obj[item.name] = item.value;
        console.log(obj);

		}

    }
    var json= JSON.stringify(obj);
    console.log(json);
    return json;
};
*/


function alerter() {
	document.getElementById("cost").value = parseFloat(
			document.getElementById("cost").value).toFixed(2);
	var amount = document.getElementById("cost").value;

	var event = document.getElementById("typeOfEvent").value;

	switch (event) {
	case "1":
		document.getElementById("reimbursementamount").value = parseFloat(
				(amount * .80)).toFixed(2);
		//document.getElementById("reimbamtEst").value = parseFloat(
		//		(amount )).toFixed(2);
		;
		break;
	case "2":
		document.getElementById("reimbursementamount").value = parseFloat(
				(amount * .60)).toFixed(2);
		//document.getElementById("reimbamtEst").value = parseFloat(
		//		(amount * .60)).toFixed(2);
		break;
	case "3":
		document.getElementById("reimbursementamount").value = parseFloat(
				(amount * .75)).toFixed(2);
		//document.getElementById("reimbamtEst").value = parseFloat(
		//		(amount * .80)).toFixed(2);
		break;
	case "4":
		document.getElementById("reimbursementamount").value = parseFloat(
				amount ).toFixed(2);
		//document.getElementById("reimbamtEst").value = parseFloat(
		//		amount * .75)
		//		.toFixed(2);
		break;
	case "5":
		document.getElementById("reimbursementamount").value = parseFloat(
				(amount * .90)).toFixed(2);
		//document.getElementById("reimbamtEst").value = parseFloat(
		//		(amount * .90)).toFixed(2);
		break;
	case "6":
		document.getElementById("reimbursementamount").value = parseFloat(
				(amount * .30)).toFixed(2);
		//document.getElementById("reimbamtEst").value = parseFloat(
		//		(amount * .30)).toFixed(2);
		break;
	default:
		console.log("Reimbursement Amount Error");
	}
}