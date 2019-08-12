// JavaScript source code

var ckObj = {
}
function loadNotifications(allNotifs) {
    var curRow = 1;
    console.log("in loadForms");
    console.log(allNotifs);
    var formTable = document.getElementById("lefttable");
    console.log("in for loop");
    for (var i = 0; i < allNotifs.length; i++) {

        var newRow = formTable.insertRow(curRow);
        var notifId = newRow.insertCell(0);
        var notifTime = new Date(allNotifs[i].time);
        var formId = newRow.insertCell(1);
        var notifCont = newRow.insertCell(2);
        var notifTimeCell = newRow.insertCell(3);
        var notifCheck = newRow.insertCell(4);
        notifId.innerHTML = allNotifs[i].id;
        formId.innerHTML = allNotifs[i].formID;
        notifCont.innerHTML = allNotifs[i].content;
        notifTimeCell.innerHTML = notifTime;
        if (allNotifs[i].checked == 0) {
            notifCheck.innerHTML = "No";
        }
        else {
            notifCheck.innerHTML = "Yes";
        }
    }


}
function getNotifications() {
    console.log("In get notifications function!");
    var xhr = new XMLHttpRequest();
    var notifs = '';
    xhr.onreadystatechange = function () {
        console.log("In ORSC " + xhr.readyState + xhr.status);
        if (xhr.readyState == 4 && xhr.status == 200) {
            console.log(xhr.responseText);
            notifs = JSON.parse(xhr.responseText);
            var ck = decodeURIComponent(document.cookie);
            var cks = ck.split(';');
            for (var i = 0; i < cks.length; i++) {
                var tempCk = cks[i].split('=');
                tempCk[0] = tempCk[0].trim();
                tempCk[1] = tempCk[1].trim();
                ckObj[tempCk[0]] = tempCk[1];
                console.log(tempCk[0]);
                console.log(ckObj[tempCk[0]]);
            }
            console.log(notifs);
            loadNotifications(notifs);
            // console.log(forms);
        }
    }
    xhr.open("GET", "http://localhost:8080/TRMS/getnotifications", true);
    xhr.send();
}

window.onload = function () {
    var forms = getNotifications();
    console.log(forms);
}