<<<<<<< HEAD
function viewRequests(){
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4 && xhr.status==200){
			var tArray = JSON.parse(xhr.responseText);
			console.log(tArray);
			var index;
			
			var tbody = document.getElementById("tbody");
			
			for(index in tArray){
				var row = tbody.insertRow(0);
				row.insertCell(0).innerHTML = tArray[index].id;
				row.insertCell(1).innerHTML = tArray[index].owner;
				row.insertCell(2).innerHTML = tArray[index].changedDate;
				
				if(tArray[index].status===10){
					row.insertCell(3).innerHTML = "Canceled";
				}else if(tArray[index].status===9){
					row.insertCell(3).innerHTML = "Approved";
				}else if(tArray[index].status===8){
					row.insertCell(3).innerHTML = "Denied";
				}else if(tArray[index].status===7){
					row.insertCell(3).innerHTML = "Waiting for proof of passing";
				}else{
					row.insertCell(3).innerHTML = "Pending";
				}
			}
			
		}
	}
	
	console.log("outside");
	xhr.open("POST", "viewrequests", true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send();
	
}

function logout(){
	
	var xhr = new XMLHttpRequest();
	
		xhr.onreadystatechange = function(){
			if(xhr.readyState==4 && xhr.status==200){
				
			}
	}
	
	xhr.open("POST", "logout", true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send();
	
}

window.onload = function(){
	viewRequests();
	document.getElementById("logout").addEventListener("click", logout, false);
	document.getElementById("submitForm")
=======
// var result = document.getElementByID("cell");
// result.addEventListener("click", modal)
// function modal(){
// 	var leftTable = document.getElementByID("lefttable");
// 	console.log("here");
// }

function addcell() {
    var formTable = document.getElementById("lefttable");
    var numRows = formTable.rows.length;
    var newRow = formTable.insertRow(numRows);
    var cell1 = newRow.insertCell(0);
    var cell2 = newRow.insertCell(1);
    var cell3 = newRow.insertCell(2);
    var cell4 = newRow.insertCell(3);
    cell1.innerHTML = "cell" + (numRows) + "entry1";
    cell2.innerHTML = "cell" + (numRows) + "entry2";
    cell3.innerHTML = "cell" + (numRows) + "entry3";
    cell4.innerHTML = "cell" + (numRows) + "entry4";}

function loadForms(allForms){
    console.log("in loadForms");
    console.log(allForms);
    var formTable = document.getElementById("lefttable");
    var curRow = 1;
   // var k = allForms.split(":");
    for (var i = 0; i < allForms.length; i++) {
        var newRow = formTable.insertRow(curRow);
        var formId = newRow.insertCell(0);
        var appDate = newRow.insertCell(1);
        var courseDate = newRow.insertCell(2);
        var loca = newRow.insertCell(3);
        var dept = newRow.insertCell(4);
        var cost = newRow.insertCell(5);
        var typeEvent = newRow.insertCell(6);
        var stat = newRow.insertCell(7);
        formId.innerHTML = allForms[i].id;
        appDate.innerHTML = new Date(allForms[i].openDateTime);
        courseDate.innerHTML = new Date(allForms[i].courseStart);
        loca.innerHTML = allForms[i].location;
        dept.innerHTML = allForms[i].deptId;
        cost.innerHTML = "$" + allForms[i].cost;
        typeEvent.innerHTML = allForms[i].typeOfEvent;
        stat.innerHTML = allForms[i].status;
        curRow++;
       // console.log(allForms[i].id);
        //console.log(allForms[i]);
    }
    // if(userId != null){
    //     document.getElementById("userId").innerHTML = userId.id;
    // } else {
    //     document.getElementById("vgName").innerHTML = "There is no record associated with that ID";
    // }
}

function getForms() {
    console.log("In get User function!");
    var xhr = new XMLHttpRequest();
    var forms = '';
    xhr.onreadystatechange = function () {
        console.log("In ORSC " + xhr.readyState + xhr.status);
        if (xhr.readyState == 4 && xhr.status == 200) {
           // console.log(xhr.responseText);
            forms = JSON.parse(xhr.responseText);
            loadForms(forms);
           // console.log(forms);
        }
    }
    xhr.open("GET", "http://localhost:8080/TRMS/getuserforms", true);
    xhr.send();
}

window.onload = function(){
    var curUser = getForms();
    console.log(curUser);   
>>>>>>> 70f30c7b85efce33a43b0f696ea5710eb5ec9abc
}