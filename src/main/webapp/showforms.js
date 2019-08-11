// var result = document.getElementByID("cell");
// result.addEventListener("click", modal)
// function modal(){
// 	var leftTable = document.getElementByID("lefttable");
// 	console.log("here");
// }
var curFormId;
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
function compare(a, b) {
    if (a.status < b.courseDate)
        return -1;
    if (a.status > b.courseDate)
        return 1;
    return 0;
}
function gradePass(cur) {
    curFormId = cur.currentTarget.parentElement.firstChild.innerHTML;
    console.log("Passing form " + curFormId);
    var curRow;
    var allRows = document.getElementById("awaitinggrade").children[0].children;
    for (var i = 0; i < allRows.length; i++) {
        curRow = allRows[i].children;
        if (curRow[0].innerHTML == curFormId) {
            console.log("Form " + curRow[0].innerHTML + " found");
            curRow[7].innerHTML = "Approved";
            //function postForm() {
                alert("in post form");

                //let vg = document.getElementById("vgForm").submit;
                var xhr2 = new XMLHttpRequest();
                xhr2.onreadystatechange = function () {
                    console.log("in post form on ready change");
                    console.log("in ORSC " + xhr2.readyState + xhr2.status);
                    if (xhr2.readyState == 4 && xhr2.status == 200) {
                        console.log(xhr.responseText);
                    }
                }
                xhr2.open("POST", "http://localhost:8080/TRMS/form", false);
                var trash = jsonBuilder();
                alert(trash);
                xhr2.send(trash); 
            

            //function jsonBuiilder() {
                var elements = document.getElementById("form").elements;
                //var elements = document.getElementsByClassName("form-control");
                var obj = {};
                for (var i = 0 ; i < elements.length; i++) {
                    var item = elements.item(i);
                    console.log(item.tagName + " is type");
                    if ((item.tagName == "INPUT" || item.tagName == "SELECT") && item.id != "reimbursementamount") {
                        obj[item.name] = item.value;
                        console.log(obj);

                    }

                }
                var json = JSON.stringify(obj);
                console.log(json);
                return json;
        }
    }
    //cur.currentTarget.removeEventListener("click", gradePass, false);
}
function submitGrade(cur) {
    console.log(curFormId);
    var getGrade = document.getElementById("gradeInBox").value;
    console.log(getGrade);
}
function gradeFail(cur) {
    curFormId = cur.currentTarget.parentElement.firstChild.innerHTML;
    console.log("Failing form " + curFormId);
    var curRow;
    var allRows = document.getElementById("awaitinggrade").children[0].children;
    for (var i = 0; i < allRows.length; i++) {
        curRow = allRows[i].children;
        if (curRow[0].innerHTML == curFormId) {
            console.log("Form " + curRow[0].innerHTML + " found");
            curRow[7].innerHTML = "Denied";
        }
    }
    //cur.currentTarget.removeEventListener("click", gradeFail, false);
    //curFormId = cur.currentTarget.parentElement.firstChild.innerHTML;
    //if (document.getElementById("gradeInputId") != null) {
    //    var prevInput = document.getElementById("gradeInputId");
    //    prevInput.parentElement.removeChild(prevInput);
    //}
    //var gradeIn = document.createElement("div");
    //gradeIn.id = "gradeInputId";
    //gradeIn.innerHTML = "Input grade for form " + curFormId +
    //    ": <input type='text' name='gradeInBox' id='gradeInBox'/>" +
    //    "<input type='submit' id='gradeInSubmit'/>";
    //document.getElementById("gradetable").appendChild(gradeIn);
    //document.getElementById("gradeInSubmit").addEventListener("click", submitGrade, false);
    
}
function loadForms(allForms){
    console.log("in loadForms");
    console.log(allForms);
    allForms.sort(compare);


    var formTable = document.getElementById("awaitinggrade");
    var curRow = 1;
    var j = 3;
    for (var i = 0; i < allForms.length; i++) {
        if (allForms[i].status == j) {
            var newRow = formTable.insertRow(curRow);
            var formId = newRow.insertCell(0);
            var appDate = newRow.insertCell(1);
            var courseDate = newRow.insertCell(2);
            var loca = newRow.insertCell(3);
            var dept = newRow.insertCell(4);
            var cost = newRow.insertCell(5);
            var typeEvent = newRow.insertCell(6);
            var stat = newRow.insertCell(7)
            var passButton = newRow.insertCell(8);
            var failButton = newRow.insertCell(9);
            formId.innerHTML = allForms[i].id;
            appDate.innerHTML = new Date(allForms[i].openDateTime);
            courseDate.innerHTML = new Date(allForms[i].courseStart);
            loca.innerHTML = allForms[i].location;
            dept.innerHTML = allForms[i].deptName;
            cost.innerHTML = "$" + allForms[i].cost;
            passButton.innerHTML = "<input type='submit'/>";
            failButton.innerHTML = "Passed";

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
                    typeEvent.innerHTML = "Certification Prep Class";
                    break;
                case "4":
                    typeEvent.innerHTML = "Seminar";
                    break;
                case "5":
                    typeEvent.innerHTML = "Other";
                    break;
            }
            switch (allForms[i].status) {
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

            }
            passButton.innerHTML = "Click if passed"
            passButton.style = "background:#00ff00";
            passButton.addEventListener("click", gradePass, false);
            failButton.innerHTML = "Click if failed"
            failButton.style = "background:#ff4d4d";
            failButton.addEventListener("click", gradeFail, false);
            curRow++;
        }
    }

    formTable = document.getElementById("awaitingsuper");
    var curRow = 1;
    j = 0;
    for (var i = 0; i < allForms.length; i++) {
            if (allForms[i].status == j) {
                var newRow = formTable.insertRow(curRow);
                var formId = newRow.insertCell(0);
                var appDate = newRow.insertCell(1);
                var courseDate = newRow.insertCell(2);
                var loca = newRow.insertCell(3);
                var dept = newRow.insertCell(4);
                var cost = newRow.insertCell(5);
                var typeEvent = newRow.insertCell(6);
                formId.innerHTML = allForms[i].id;
                appDate.innerHTML = new Date(allForms[i].openDateTime);
                courseDate.innerHTML = new Date(allForms[i].courseStart);
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
                        typeEvent.innerHTML = "Certification Prep Class";
                        break;
                    case "4":
                        typeEvent.innerHTML = "Seminar";
                        break;
                    case "5":
                        typeEvent.innerHTML = "Other";
                        break;
                }
                curRow++;
            }
    }

    formTable = document.getElementById("awaitingdept");
    var curRow = 1;
    j = 1;
    for (var i = 0; i < allForms.length; i++) {
        if (allForms[i].status == j) {
            var newRow = formTable.insertRow(curRow);
            var formId = newRow.insertCell(0);
            var appDate = newRow.insertCell(1);
            var courseDate = newRow.insertCell(2);
            var loca = newRow.insertCell(3);
            var dept = newRow.insertCell(4);
            var cost = newRow.insertCell(5);
            var typeEvent = newRow.insertCell(6);
            formId.innerHTML = allForms[i].id;
            appDate.innerHTML = new Date(allForms[i].openDateTime);
            courseDate.innerHTML = new Date(allForms[i].courseStart);
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
                    typeEvent.innerHTML = "Certification Prep Class";
                    break;
                case "4":
                    typeEvent.innerHTML = "Seminar";
                    break;
                case "5":
                    typeEvent.innerHTML = "Other";
                    break;
            }
            curRow++;
        }
    }

    formTable = document.getElementById("awaitingbenco");
    var curRow = 1;
    j = 2;
    for (var i = 0; i < allForms.length; i++) {
        if (allForms[i].status == j) {
            var newRow = formTable.insertRow(curRow);
            var formId = newRow.insertCell(0);
            var appDate = newRow.insertCell(1);
            var courseDate = newRow.insertCell(2);
            var loca = newRow.insertCell(3);
            var dept = newRow.insertCell(4);
            var cost = newRow.insertCell(5);
            var typeEvent = newRow.insertCell(6);
            formId.innerHTML = allForms[i].id;
            appDate.innerHTML = new Date(allForms[i].openDateTime);
            courseDate.innerHTML = new Date(allForms[i].courseStart);
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
                    typeEvent.innerHTML = "Certification Prep Class";
                    break;
                case "4":
                    typeEvent.innerHTML = "Seminar";
                    break;
                case "5":
                    typeEvent.innerHTML = "Other";
                    break;
            }
            curRow++;
        }
    }

    formTable = document.getElementById("approved");
    var curRow = 1;
    j = 4;
    for (var i = 0; i < allForms.length; i++) {
        if (allForms[i].status == j) {
            var newRow = formTable.insertRow(curRow);
            var formId = newRow.insertCell(0);
            var appDate = newRow.insertCell(1);
            var courseDate = newRow.insertCell(2);
            var loca = newRow.insertCell(3);
            var dept = newRow.insertCell(4);
            var cost = newRow.insertCell(5);
            var typeEvent = newRow.insertCell(6);
            formId.innerHTML = allForms[i].id;
            appDate.innerHTML = new Date(allForms[i].openDateTime);
            courseDate.innerHTML = new Date(allForms[i].courseStart);
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
                    typeEvent.innerHTML = "Certification Prep Class";
                    break;
                case "4":
                    typeEvent.innerHTML = "Seminar";
                    break;
                case "5":
                    typeEvent.innerHTML = "Other";
                    break;
            }
            curRow++;
        }
    }

    formTable = document.getElementById("denied");
    var curRow = 1;
    j = -1;
    for (var i = 0; i < allForms.length; i++) {
        if (allForms[i].status == j) {
            var newRow = formTable.insertRow(curRow);
            var formId = newRow.insertCell(0);
            var appDate = newRow.insertCell(1);
            var courseDate = newRow.insertCell(2);
            var loca = newRow.insertCell(3);
            var dept = newRow.insertCell(4);
            var cost = newRow.insertCell(5);
            var typeEvent = newRow.insertCell(6);
            formId.innerHTML = allForms[i].id;
            appDate.innerHTML = new Date(allForms[i].openDateTime);
            courseDate.innerHTML = new Date(allForms[i].courseStart);
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
                    typeEvent.innerHTML = "Certification Prep Class";
                    break;
                case "4":
                    typeEvent.innerHTML = "Seminar";
                    break;
                case "5":
                    typeEvent.innerHTML = "Other";
                    break;
            }
            curRow++;
        }
    }

  
    
}

function getForms() {
    console.log("In get User function!");
    var xhr = new XMLHttpRequest();
    var forms = '';
    xhr.onreadystatechange = function () {
        console.log("In ORSC " + xhr.readyState + xhr.status);
        if (xhr.readyState == 4 && xhr.status == 200) {
           // console.log(xhr.responseText);
            forms = JSON.parse(xhr.responseText);
            loadForms(forms);
           // console.log(forms);
        }
    }
    xhr.open("GET", "http://localhost:8080/TRMS/getuserforms", true);
    xhr.send();
}

window.onload = function(){
    var curUser = getForms();
    console.log(curUser);
}