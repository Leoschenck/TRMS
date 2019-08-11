<<<<<<< HEAD
function displayForm(){
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4 && xhr.status==200){
			var et = JSON.parse(xhr.responseText);
			ticketid = et.ticket.id;
			
			document.getElementById("ticketid").innerHTML = et.ticket.id;
			document.getElementById("name").innerHTML = et.employee.firstname + " " + et.employee.lastname;
			document.getElementById("email").innerHTML = et.employee.email;
			document.getElementById("phone").innerHTML = et.employee.phone;
			document.getElementById("employeeid").innerHTML = et.employee.id;
			document.getElementById("department").innerHTML = et.employee.department;
			document.getElementById("position").innerHTML = et.employee.position;
			document.getElementById("claimed").innerHTML = et.employee.claimedReimbursement;
			document.getElementById("pending").innerHTML = et.employee.pendingReimbursement;
			document.getElementById("remaining").innerHTML = et.employee.remainingReimbursement;
			document.getElementById("estimate").innerHTML = et.ticket.changedAmount;
			document.getElementById("cost").innerHTML = et.ticket.eventCost;
			document.getElementById("type").innerHTML = et.ticket.eventType;
			document.getElementById("format").innerHTML = et.ticket.gradeFormat;
			document.getElementById("reqGrade").innerHTML = et.ticket.reqGrade;
			document.getElementById("startDate").innerHTML = et.ticket.startDate;
			document.getElementById("endDate").innerHTML = et.ticket.endDate;
			document.getElementById("startTime").innerHTML = et.ticket.startTime;
			document.getElementById("endTime").innerHTML = et.ticket.endTime;
			document.getElementById("frequency").innerHTML = et.ticket.frequency;
			document.getElementById("workmissed").innerHTML = et.ticket.workMissed;
			document.getElementById("description").innerHTML = et.ticket.description;
			document.getElementById("justification").innerHTML = et.ticket.justification;
			document.getElementById("address").innerHTML = et.ticket.address;
			document.getElementById("state").innerHTML = et.ticket.state;
			document.getElementById("city").innerHTML = et.ticket.city;
			document.getElementById("zip").innerHTML = et.ticket.zip;
			console.log(et.employee.firstname);
		}
	}
	
	console.log("outside");
	xhr.open("GET", "view", true);
	xhr.setRequestHeader('Content-Type', 'form');
	xhr.send();
	
}

function approve(){
	
	var xhr = new XMLHttpRequest();
	
	console.log(ticketid);
	
	var form = {
		decision: "approve",
		id: ticketid
	}
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4 && xhr.status==200){
			
		}
	}
	
	xhr.open("POST", "decision", true);
	xhr.setRequestHeader('Content-Type', 'form');
	xhr.send("form="+JSON.stringify(form));
	
}

<<<<<<< HEAD
function deny(){
	
	var xhr = new XMLHttpRequest();
	
	console.log(ticketid);
	
	var form = {
		decision: "deny",
		id: ticketid
	}
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4 && xhr.status==200){
			
		}
	}
	
	xhr.open("POST", "decision", true);
	xhr.setRequestHeader('Content-Type', 'form');
	xhr.send("form="+JSON.stringify(form));
	
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
	
=======
function loadForms(allForms){
    console.log("in loadForms");

    console.log(allForms);
=======
// var result = document.getElementByID("cell");
// result.addEventListener("click", modal)
// function modal(){
// 	var leftTable = document.getElementByID("lefttable");
// 	console.log("here");
// }
var curFormId;
function gradeInput(cur){
    curFormId = cur.currentTarget.parentElement.firstChild.innerHTML;
    if (document.getElementById("gradeInputId") != null) {
        var prevInput = document.getElementById("gradeInputId");
        prevInput.parentElement.removeChild(prevInput);
    }
    var gradeIn = document.createElement("div");
    gradeIn.id = "gradeInputId";
    gradeIn.innerHTML = "Input grade for form " + curFormId +
        ": <input type='text' name='gradeInBox' id='gradeInBox'/>" +
        "<input type='submit' id='gradeInSubmit'/>";
    document.getElementById("gradetable").appendChild(gradeIn);
    document.getElementById("gradeInSubmit").addEventListener("click", submitGrade, false);
    
}
function submitGrade(cur) {
    console.log(curFormId);
    var getGrade = document.getElementById("gradeInBox").value;
    console.log(getGrade);
}
function approveForm(cur) {
    curFormId = cur.currentTarget.parentElement.firstChild.innerHTML;
    console.log("Approving form " + curFormId);
    var curRow;
    var allRows = document.getElementById("lefttable").children[0].children;
    curRow = cur.currentTarget.parentElement.children;
    console.log("Form " + curRow[0].innerHTML + " found");
    curRow[7].innerHTML = "Approved for next step"
    /*switch (curRow[7].innerHTML) {
        case "Awaiting Supervisor Approval":
            curRow[7].innerHTML = "Awaiting Department Approval";
            break;
        case "Awaiting Department Approval":
            curRow[7].innerHTML = "Awaiting BenCo Approval";
            break;
        case "Awaiting BenCo Approval":
            curRow[7].innerHTML = "Awaiting Grade";
            break;
        case "Denied":
            return;
    }*/

    var xhr2 = new XMLHttpRequest();
    xhr2.onreadystatechange = function () {
        console.log("in FORM APPROVE on ready change");
        console.log("in ORSC " + xhr2.readyState + xhr2.status);
        if (xhr2.readyState == 4 && xhr2.status == 200) {
            //  console.log(xhr2.responseText);
        }
    }
    xhr2.open("POST", "http://localhost:8080/TRMS/approval", false);
    var obj = {};
    obj["formId"] = curFormId;
    obj["approved"] = 1;
    var trash = JSON.stringify(obj)
    xhr2.send(trash);

    //cur.currentTarget.removeEventListener("click", approveForm, false);
    //cur.currentTarget.parentElement.children[9].removeEventListener("click", denyForm, false);
}
function denyForm(cur) {
    curFormId = cur.currentTarget.parentElement.firstChild.innerHTML;
    console.log("Denying form " + curFormId);
    var curRow;
    var allRows = document.getElementById("lefttable").children[0].children;
    curRow = cur.currentTarget.parentElement.children;
    console.log("Form " + curRow[0].innerHTML + " found");
    curRow[7].innerHTML = "Denied";

    var xhr2 = new XMLHttpRequest();
    xhr2.onreadystatechange = function () {
        console.log("in FORM DENY on ready change");
        console.log("in ORSC " + xhr2.readyState + xhr2.status);
        if (xhr2.readyState == 4 && xhr2.status == 200) {
            //  console.log(xhr2.responseText);
        }
    }
    xhr2.open("POST", "http://localhost:8080/TRMS/approval", false);
    var obj = {};
    obj["formId"] = curFormId;
    obj["approved"] = 0;
    var trash = JSON.stringify(obj)
    xhr2.send(trash);

    //cur.currentTarget.removeEventListener("click", denyForm, false);
    //cur.currentTarget.parentElement.children[8].removeEventListener("click", approveForm, false);
}
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

function loadApprovalForms(allForms){
    console.log("in loadForms");
    console.log(allForms);
    var formTable = document.getElementById("lefttable");
    var curRow = 1;
    for (var i = 0; i < allForms.length; i++) {
    	console.log("in for loop");
        var newRow = formTable.insertRow(curRow);
        var formId = newRow.insertCell(0);
        var appDate = newRow.insertCell(1);
        var courseDate = newRow.insertCell(2);
        var loca = newRow.insertCell(3);
        var dept = newRow.insertCell(4);
        var cost = newRow.insertCell(5);
        var typeEvent = newRow.insertCell(6);
        var approveButton = newRow.insertCell(7);
        var denyButton = newRow.insertCell(8);
        var stat = newRow.insertCell(7);
        formId.innerHTML = allForms[i].id;
        appDate.innerHTML = new Date(allForms[i].openDateTime);
        courseDate.innerHTML = new Date(allForms[i].courseStart);
        loca.innerHTML = allForms[i].location;
        dept.innerHTML = allForms[i].deptName;
        cost.innerHTML = "$" + allForms[i].cost;

        switch(allForms[i].typeOfEvent){
            case "0":
                typeEvent.innerHTML = "Certification";
                break;
            case "1":
                typeEvent.innerHTML = "Technical Training";
                break;
            case "2":
                typeEvent.innerHTML = "University Course";
                break;
            case "3":
                typeEvent.innerHTML = "Certification Prep Classes";
                break;
            case "4":
                typeEvent.innerHTML = "Seminar";
                break;
            case "5":
                typeEvent.innerHTML = "Other";
                break;
        }
        approveButton.innerHTML = "Approve"
        approveButton.style = "background:#00ff00";
        approveButton.addEventListener("click", approveForm, false);
        denyButton.innerHTML = "Deny"
        denyButton.style = "background:#ff4d4d";
        denyButton.addEventListener("click", denyForm, false);
        stat.innerHTML = "Awaiting your approval"
        /*switch (allForms[i].status) {
            case 0:
                stat.innerHTML = "Awaiting Supervisor Approval";
                break;
            case 1:
                stat.innerHTML = "Awaiting Department Approval";
                break;
            case 2:
                stat.innerHTML = "Awaiting BenCo Approval";
                break;
            case 3:
                stat.innerHTML = "Awaiting Grade";
                break;
            case 4:
                stat.innerHTML = "Approved";
                break;
            case -1:
                stat.innerHTML = "Denied";
                break;

        }*/
        curRow++;
       // console.log(allForms[i].id);
        //console.log(allForms[i]);
    }
>>>>>>> 529e171776354a7ae9c350a400755fc7809e7aeb
    // if(userId != null){
    //     document.getElementById("userId").innerHTML = userId.id;
    // } else {
    //     document.getElementById("vgName").innerHTML = "There is no record associated with that ID";
    // }
}

function getApprovalForms() {
    console.log("In get User function!");
    var xhr = new XMLHttpRequest();
    var forms = '';
    xhr.onreadystatechange = function () {
        console.log("In ORSC " + xhr.readyState + xhr.status);
        if (xhr.readyState == 4 && xhr.status == 200) {
           // console.log(xhr.responseText);
            forms = JSON.parse(xhr.responseText);
            loadApprovalForms(forms);
           // console.log(forms);
        }
    }
<<<<<<< HEAD
    xhr.open("GET", "http://localhost:8080/TRMS/viewforms", true);
=======
    xhr.open("GET", "http://localhost:8080/TRMS/getapprovableforms", true);
>>>>>>> 529e171776354a7ae9c350a400755fc7809e7aeb
    xhr.send();
>>>>>>> 56bf37ad14117952d75193edbab82053e4a7611e
}



window.onload = function(){
<<<<<<< HEAD
<<<<<<< HEAD
	var ticketid;
	displayForm();
	document.getElementById("approve").addEventListener("click", approve, false);
	document.getElementById("deny").addEventListener("click", deny, false);
	document.getElementById("logout").addEventListener("click", logout, false);
}
=======
	var curUser = getForms();
    console.log(curUser)
}
>>>>>>> 56bf37ad14117952d75193edbab82053e4a7611e
=======
    var forms = getApprovalForms();
    console.log(forms);   
}
>>>>>>> 529e171776354a7ae9c350a400755fc7809e7aeb
