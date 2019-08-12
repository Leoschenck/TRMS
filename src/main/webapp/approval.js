// var result = document.getElementByID("cell");
// result.addEventListener("click", modal)
// function modal(){
// 	var leftTable = document.getElementByID("lefttable");
// 	console.log("here");
// }


var ckObj = {
}
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
function openQuestion(cur) {
    var rowList = cur.currentTarget.parentElement.parentElement.children;
    console.log(rowList);
    var curFormId = cur.currentTarget.parentElement.firstChild.innerHTML;
    console.log(curFormId);
    for (var i = 0; i < rowList.length; i++) {
        console.log(i);
        if (rowList[i].children[0] != null) {
            if (rowList[i].children[0].innerHTML == curFormId) {
                break;
                console.log(rowList[i].firstChild.innerHTML);
            }
        }
    }
    console.log(i);
    if (document.getElementById("questBox") != null) {
        var prevInput = document.getElementById("questBox");
        prevInput.parentElement.removeChild(prevInput);
        prevInput = document.getElementById("questText");
        prevInput.parentElement.removeChild(prevInput);
        prevInput = document.getElementById("questSubmit");
        prevInput.parentElement.removeChild(prevInput);
        console.log("removed child");
    }
    
    var myTable = document.getElementById("lefttable");
    var questRow = myTable.insertRow(i + 1);
    var questTextCell = questRow.insertCell(0);
    var questBoxCell = questRow.insertCell(1);
    var questSubmitCell = questRow.insertCell(2);
    questTextCell.id = "questText";
    questTextCell.colSpan = "2";
    questTextCell.innerHTML = "Please type question: ";

    questBoxCell.id = "questBox";
    questBoxCell.colSpan = "8";
    questBoxCell.innerHTML = "<textarea name='message' id='questBoxArea' rows='3' cols='100'>";

    questSubmitCell.id = "questSubmit";
    questSubmitCell.colSpan = "1";
    questSubmitCell.innerHTML = "<input type='submit' id='questSubmitButton' name='ed'/>"
    document.getElementById("questSubmitButton").addEventListener("click", submitQuestion, false);
}
function submitQuestion(cur) {
    console.log(document.getElementById("questBoxArea").value);
    document.getElementById("questText").innerHTML = "Question submitted: ";
    document.getElementById("questText").style="font-weight:bold";
    document.getElementById("questBox").innerHTML = document.getElementById("questBoxArea").value;
    document.getElementById("questBox").style="font-weight:bold";
    document.getElementById("questSubmitButton").removeEventListener("click", submitQuestion, false);
    document.getElementById("questSubmitButton").style = "visibility:hidden";
	var xhr2 = new XMLHttpRequest();
    xhr2.onreadystatechange = function () {
        console.log("in FORM Question on ready change");
        console.log("in ORSC " + xhr2.readyState + xhr2.status);
        if (xhr2.readyState == 4 && xhr2.status == 200) {
            //  console.log(xhr2.responseText);
        }
    }
    xhr2.open("POST", "http://localhost:8080/TRMS/approval", false);
    var obj = {};
    obj["formId"] = curFormId;
    obj["attachedReasoning"] = document.getElementById("questBox").innerHTML;
    var trash = JSON.stringify(obj)
    xhr2.send(trash);
}
function approveForm(cur) {
	console.log("benco: " + ckObj["isBenco"]);
    if (true) {
        curFormId = cur.currentTarget.parentElement.firstChild.innerHTML;
        console.log("Approving form " + curFormId);
        var curRow;
        var allRows = document.getElementById("lefttable").children[0].children;
        curRow = cur.currentTarget.parentElement.children;
        console.log("Form " + curRow[0].innerHTML + " found");
        curRow[7].innerHTML = "Approved for next step";

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
    }
    else {
        var rowList = cur.currentTarget.parentElement.parentElement.children;
        console.log(rowList);
        var curFormId = cur.currentTarget.parentElement.firstChild.innerHTML;
        console.log(curFormId);
        for (var i = 0; i < rowList.length; i++) {
            console.log(i);
            if (rowList[i].children[0] != null) {
                if (rowList[i].children[0].innerHTML == curFormId) {
                    break;
                    console.log(rowList[i].firstChild.innerHTML);
                }
            }
        }
        console.log(i);
        if (document.getElementById("subsBox") != null) {
            var prevInput = document.getElementById("subsBox");
            prevInput.parentElement.removeChild(prevInput);
            prevInput = document.getElementById("subsText");
            prevInput.parentElement.removeChild(prevInput);
            prevInput = document.getElementById("subsSubmit");
            prevInput.parentElement.removeChild(prevInput);
            console.log("removed child");
        }
        var myTable = document.getElementById("lefttable");
        var subsRow = myTable.insertRow(i + 1);
        var subsTextCell = subsRow.insertCell(0);
        var subsBoxCell = subsRow.insertCell(1);
        var subsReasCell = subsRow.insertCell(2);
        var subsSubmitCell = subsRow.insertCell(3);
        subsTextCell.id = "subsText";
        subsTextCell.colSpan = "2";
        subsTextCell.innerHTML = "Input optional subsidy amount for this user: ";
        
        subsBoxCell.id = "subsBox";
        subsBoxCell.colSpan = "1";
        subsBoxCell.innerHTML = "<input type='number' id='subsBoxArea' placeholder='0'>";

        subsReasCell.id = "subsReas";
        subsReasCell.colSpan = "7";
        subsReasCell.innerHTML = "<textarea name='message' id='subsReasBoxArea' rows='3' cols='80' placeholder='Reason for subsidy'>";

        subsSubmitCell.id = "subsSubmit";
        subsSubmitCell.colSpan = "1";
        subsSubmitCell.innerHTML = "<input type='submit' id='subsSubmitButton' name='ed'/>"
        document.getElementById("subsSubmitButton").addEventListener("click", submitSubsidy, false);
    }


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

    //cur.currentTarget.removeEventListener("click", approveForm, false);
    //cur.currentTarget.parentElement.children[9].removeEventListener("click", denyForm, false);
}
function submitSubsidy(cur) {
    console.log(document.getElementById("subsBoxArea").value);
    cur.currentTarget.parentElement.parentElement.previousSibling.children[7].innerHTML = "Denied";
    document.getElementById("subsText").innerHTML = "Subsidy submitted of amount: ";
    document.getElementById("subsText").style = "font-weight:bold";
    document.getElementById("subsBox").innerHTML = document.getElementById("subsBoxArea").value;
    document.getElementById("subsBox").style = "font-weight:bold";
    document.getElementById("subsReas").innerHTML = document.getElementById("subsReasBoxArea").value;
    document.getElementById("subsReas").style = "font-weight:bold";
    document.getElementById("subsSubmitButton").removeEventListener("click", submitSubsidy, false);
    document.getElementById("subsSubmitButton").style = "visibility:hidden";
    var xhr2 = new XMLHttpRequest();
    xhr2.onreadystatechange = function () {
        console.log("in FORM Deny Reason on ready change");
        console.log("in ORSC " + xhr2.readyState + xhr2.status);
        if (xhr2.readyState == 4 && xhr2.status == 200) {
            //  console.log(xhr2.responseText);
        }
    }
    xhr2.open("POST", "http://localhost:8080/TRMS/approval", false);
    var obj = {};
    obj["formId"] = curFormId;
    obj["approved"] = 1;
    obj["subsAmt"] = document.getElementById("subsBox").innerHTML;
    obj["attachedReasoning"] = document.getElementById("subsReas").innerHTML;
    var trash = JSON.stringify(obj);
    xhr2.send(trash);
}

function denyForm(cur) {
    var rowList = cur.currentTarget.parentElement.parentElement.children;
    console.log(rowList);
    var curFormId = cur.currentTarget.parentElement.firstChild.innerHTML;
    console.log(curFormId);
    for (var i = 0; i < rowList.length; i++) {
        console.log(i);
        if (rowList[i].children[0] != null) {
            if (rowList[i].children[0].innerHTML == curFormId) {
                break;
                console.log(rowList[i].firstChild.innerHTML);
            }
        }
    }
    console.log(i);
    if (document.getElementById("reasBox") != null) {
        var prevInput = document.getElementById("reasBox");
        prevInput.parentElement.removeChild(prevInput);
        prevInput = document.getElementById("reasText");
        prevInput.parentElement.removeChild(prevInput);
        prevInput = document.getElementById("reasSubmit");
        prevInput.parentElement.removeChild(prevInput);
        console.log("removed child");
    }
    
    var myTable = document.getElementById("lefttable");
    var reasRow = myTable.insertRow(i + 1);
    var reasTextCell = reasRow.insertCell(0);
    var reasBoxCell = reasRow.insertCell(1);
    var reasSubmitCell = reasRow.insertCell(2);
    reasTextCell.id = "reasText";
    reasTextCell.colSpan = "2";
    reasTextCell.innerHTML = "Please type reason for denial: ";

    reasBoxCell.id = "reasBox";
    reasBoxCell.colSpan = "8";
    reasBoxCell.innerHTML = "<textarea name='message' id='reasBoxArea' rows='3' cols='100'>";
    
    reasSubmitCell.id = "reasSubmit";
    reasSubmitCell.colSpan = "1";
    reasSubmitCell.innerHTML = "<input type='submit' id='reasSubmitButton' name='ed'/>"
    document.getElementById("reasSubmitButton").addEventListener("click", submitReason, false);
}
function submitReason(cur) {
    console.log(document.getElementById("reasBoxArea").value);
    cur.currentTarget.parentElement.parentElement.previousSibling.children[7].innerHTML = "Denied";
    document.getElementById("reasText").innerHTML = "Reason for denial submitted: ";
    document.getElementById("reasText").style="font-weight:bold";
    document.getElementById("reasBox").innerHTML = document.getElementById("reasBoxArea").value;
    document.getElementById("reasBox").style="font-weight:bold";
    document.getElementById("reasSubmitButton").removeEventListener("click", submitReason, false);
    document.getElementById("reasSubmitButton").style = "visibility:hidden";
    var xhr2 = new XMLHttpRequest();
    xhr2.onreadystatechange = function () {
        console.log("in FORM Deny Reason on ready change");
        console.log("in ORSC " + xhr2.readyState + xhr2.status);
        if (xhr2.readyState == 4 && xhr2.status == 200) {
            //  console.log(xhr2.responseText);
        }
    }
    xhr2.open("POST", "http://localhost:8080/TRMS/approval", false);
    var obj = {};
    obj["formId"] = curFormId;
    obj["approved"] = -1;
    obj["attachedReasoning"] = document.getElementById("reasBox").innerHTML;
    var trash = JSON.stringify(obj)
    xhr2.send(trash);
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
function loadApprovalForms(allForms) {
    var curRow = 1;
    console.log("in loadForms");
    console.log(allForms);
    var formTable = document.getElementById("lefttable");
    console.log("in for loop");
    for (var i = 0; i < allForms.length; i++) {
            var applDate = new Date(allForms[i].openDateTime);
            var coursDate = new Date(allForms[i].courseStart);
            var courseMil = (coursDate.getTime() / (1000 * 60 * 60 * 24));
            var curMil = (Date.now() / (1000 * 60 * 60 * 24));
            var courseDif = courseMil - curMil;
        if (courseDif <= 7) {
            console.log(courseDif);
            var newRow = formTable.insertRow(curRow);
            var formId = newRow.insertCell(0);
            var appDate = newRow.insertCell(1);
            var courseDate = newRow.insertCell(2);
            var loca = newRow.insertCell(3);
            var dept = newRow.insertCell(4);
            var cost = newRow.insertCell(5);
            var typeEvent = newRow.insertCell(6);
            var stat = newRow.insertCell(7);
            var approveButton = newRow.insertCell(8);
            var denyButton = newRow.insertCell(9);
            var questButton = newRow.insertCell(10);
            formId.innerHTML = allForms[i].id;
            appDate.innerHTML = applDate;
            courseDate.innerHTML = coursDate;
            courseDate.style = "background:#ff8080";
            loca.innerHTML = allForms[i].location;
            dept.innerHTML = allForms[i].deptName;
            cost.innerHTML = "$" + allForms[i].cost;

            switch (allForms[i].typeOfEvent) {
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
        }
    }
    for (var i = 0; i < allForms.length; i++) {
        var applDate = new Date(allForms[i].openDateTime);
        var coursDate = new Date(allForms[i].courseStart);
        var courseMil = (coursDate.getTime() / (1000 * 60 * 60 * 24));
        var curMil = (Date.now() / (1000 * 60 * 60 * 24));
        var courseDif = courseMil - curMil;
        if (courseDif > 7) {
            console.log(courseDif);
            var newRow = formTable.insertRow(curRow);
            var formId = newRow.insertCell(0);
            var appDate = newRow.insertCell(1);
            var courseDate = newRow.insertCell(2);
            var loca = newRow.insertCell(3);
            var dept = newRow.insertCell(4);
            var cost = newRow.insertCell(5);
            var typeEvent = newRow.insertCell(6);
            var stat = newRow.insertCell(7);
            var approveButton = newRow.insertCell(8);
            var denyButton = newRow.insertCell(9);
            var questButton = newRow.insertCell(10);
            formId.innerHTML = allForms[i].id;
            appDate.innerHTML = applDate;
            courseDate.innerHTML = coursDate;
            loca.innerHTML = allForms[i].location;
            dept.innerHTML = allForms[i].deptName;
            cost.innerHTML = "$" + allForms[i].cost;

            switch (allForms[i].typeOfEvent) {
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
        }
    }

}

function getApprovalForms() {
    console.log("In get appr forms function!");
    var xhr = new XMLHttpRequest();
    var forms = '';
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
            loadApprovalForms(forms);
           // console.log(forms);
        }
    }
    xhr.open("GET", "http://localhost:8080/TRMS/getapprovableforms", true);
    xhr.send();
}

window.onload = function () {
    var forms = getApprovalForms();
    console.log(forms);   
}