// var result = document.getElementByID("cell");
// result.addEventListener("click", modal)
// function modal(){
// 	var leftTable = document.getElementByID("lefttable");
// 	console.log("here");
// }

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
    xhr.open("GET", "http://localhost:8080/TRMS/showforms", true);
    xhr.send();
}

window.onload = function(){
	var curUser = getForms();
    console.log(curUser)
}