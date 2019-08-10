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
}