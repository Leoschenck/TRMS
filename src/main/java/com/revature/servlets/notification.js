function loadUserHome(userId){
	console.log("in loadUserHome");
    
}

function getNotifications() {
    console.log("In get Notifications function!");
    var xhr = new XMLHttpRequest();
    var notifications = '';
    xhr.onreadystatechange = function () {
        //alert("In ORSC " + xhr.readyState + xhr.status);
        if (xhr.readyState == 4 && xhr.status == 200) {
            console.log(xhr.responseText);
            notifications = JSON.parse(xhr.responseText);
            loadUserHome(notifications);
            console.log(notifications);
            
        }
    }
    
    xhr.open("GET", "http://localhost:8080/TRMS/getnotifications", true);
    xhr.send();
}

window.onload = function(){
	var curUsersNotifications = getNotifications();
	console.log(curUsersNotifications)
}