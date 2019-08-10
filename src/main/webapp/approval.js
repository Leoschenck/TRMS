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
            console.log(xhr.responseText);
            forms = JSON.parse(xhr.responseText);
            loadForms(forms);
            console.log(forms);
        }
    }
    xhr.open("GET", "http://localhost:8080/TRMS/viewforms", true);
    xhr.send();
>>>>>>> 56bf37ad14117952d75193edbab82053e4a7611e
}



window.onload = function(){
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
