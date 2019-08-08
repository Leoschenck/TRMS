function loadUserHome(userId){
	console.log("in loadUserHome");
    if(userId != null){
        document.getElementById("userId").innerHTML = userId.id;
    } else {
        document.getElementById("vgName").innerHTML = "There is no record associated with that ID";
    }
}

function getUser() {
    console.log("In get User function!");
    var xhr = new XMLHttpRequest();
    var user = '';
    xhr.onreadystatechange = function () {
        console.log("In ORSC " + xhr.readyState);
        if (xhr.readyState == 4 && xhr.status == 200) {
            console.log(xhr.responseText);
            user = JSON.parse(xhr.responseText);
            loadUserHome(user);
        }
    }
    xhr.open("GET", "http://localhost:8080/TRMS/home", true);
    xhr.send();
}