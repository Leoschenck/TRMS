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
        var notifId = newRow.insertCell(1);
        var notifCont = newRow.insertCell(2);
        var notifTimeCell = newRow.insertCell(3);
        var noticCheck = newRow.insertCell(4);
        notifId.innerHTML = allNotifs[i].id;


        formId.innerHTML = allNotifs[i].id;
        appDate.innerHTML = applDate;
        courseDate.innerHTML = coursDate;
        courseDate.style = "background:#ff8080";
        loca.innerHTML = allNotifs[i].location;
        dept.innerHTML = allNotifs[i].deptName;
        cost.innerHTML = "$" + allNotifs[i].cost;

        switch (allNotifs[i].typeOfEvent) {
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
        stat.innerHTML = "Awaiting your approval";
        approveButton.innerHTML = "Approve"
        approveButton.style = "background:#00ff00";
        approveButton.addEventListener("click", approveForm, false);
        denyButton.innerHTML = "Deny"
        denyButton.style = "background:#ff4d4d";
        denyButton.addEventListener("click", denyForm, false);
        questButton.style = "background:#b3b3b3";
        questButton.innerHTML = "Submit Question";
        questButton.addEventListener("click", openQuestion, false);
        curRow++;
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
            forms = JSON.parse(xhr.responseText);
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
            loadNotifications(notifs);
            // console.log(forms);
        }
    }
    xhr.open("GET", "http://localhost:8080/TRMS/notifications", true);
    xhr.send();
}

window.onload = function () {
    var forms = getNotifications();
    console.log(forms);
}