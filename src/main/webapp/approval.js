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
function openQuestion(cur) {
    var rowList = cur.currentTarget.parentElement.parentElement.children;
    console.log(rowList);
    var curRowId = cur.currentTarget.parentElement.firstChild.innerHTML;
    console.log(curRowId);
    for (var i = 0; i < rowList.length; i++) {
        console.log(i);
        if (rowList[i].children[0] != null) {
            if (rowList[i].children[0].innerHTML == curRowId) {
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
    var rowList = cur.currentTarget.parentElement.parentElement.children;
    console.log(rowList);
    var curRowId = cur.currentTarget.parentElement.firstChild.innerHTML;
    console.log(curRowId);
    for (var i = 0; i < rowList.length; i++) {
        console.log(i);
        if (rowList[i].children[0] != null) {
            if (rowList[i].children[0].innerHTML == curRowId) {
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





                                    //curFormId = cur.currentTarget.parentElement.firstChild.innerHTML;
                                    //console.log("Denying form " + curFormId);
                                    //var curRow;
                                    //var allRows = document.getElementById("lefttable").children[0].children;
                                    //curRow = cur.currentTarget.parentElement.children;
                                    //console.log("Form " + curRow[0].innerHTML + " found");
                                    //curRow[7].innerHTML = "Denied";

                                    //var xhr2 = new XMLHttpRequest();
                                    //xhr2.onreadystatechange = function () {
                                    //    console.log("in FORM DENY on ready change");
                                    //    console.log("in ORSC " + xhr2.readyState + xhr2.status);
                                    //    if (xhr2.readyState == 4 && xhr2.status == 200) {
                                    //        //  console.log(xhr2.responseText);
                                    //    }
                                    //}
                                    //xhr2.open("POST", "http://localhost:8080/TRMS/approval", false);
                                    //var obj = {};
                                    //obj["formId"] = curFormId;
                                    //obj["approved"] = 0;
                                    ///////obj["attachedReasoning"] =
                                    //var trash = JSON.stringify(obj)
                                    //xhr2.send(trash);

    //cur.currentTarget.removeEventListener("click", denyForm, false);
    //cur.currentTarget.parentElement.children[8].removeEventListener("click", approveForm, false);
}
function submitReason(cur) {
    console.log(document.getElementById("reasBoxArea").value);
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
    obj["approved"] = 0;
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
var ckobj = {

}
function loadApprovalForms(allForms){
    console.log("in loadForms");
    console.log(allForms);
    var formTable = document.getElementById("lefttable");
    var curRow = 1;
    var ck = decodeURIComponent(document.cookie);
    var cks = ck.split(';');
    var ckobj = {
    }
    for (var i = 0; i < cks.length; i++) {
        var tempCk = cks[i].split('=');
        tempCk[0] = tempCk[0].trim();
        tempCk[1] = tempCk[1].trim();
        ckobj[tempCk[0]] = tempCk[1];
        console.log(tempCk[0]);
        console.log(ckobj[tempCk[0]]);
    }
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
        var stat = newRow.insertCell(7);
        var approveButton = newRow.insertCell(8);
        var denyButton = newRow.insertCell(9);
        var questButton = newRow.insertCell(10);
        formId.innerHTML = allForms[i].id;
        appDate.innerHTML = new Date(allForms[i].openDateTime);
        courseDate.innerHTML = new Date(allForms[i].courseStart);
        loca.innerHTML = allForms[i].location;
        dept.innerHTML = allForms[i].deptName;
        cost.innerHTML = "$" + allForms[i].cost + 0;

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
       // console.log(allForms[i].id);
        //console.log(allForms[i]);
    }
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
    xhr.open("GET", "http://localhost:8080/TRMS/getapprovableforms", true);
    xhr.send();
}

window.onload = function(){
    var forms = getApprovalForms();
    console.log(forms);   
}