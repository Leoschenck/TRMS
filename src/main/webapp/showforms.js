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
function gradeInput(cur){
    curFormId = cur.currentTarget.parentElement.firstChild.innerHTML;
    if (document.getElementById("gradeInBox") == null) {
        var gradeIn = document.createElement("div");
        gradeIn.innerHTML = "Input grade for form " + curFormId +
            ": <input type='text' name='gradeInBox' id='gradeInBox'/>" +
            "<input type='submit' id='gradeInSubmit'/>";
        document.getElementById("gradetable").appendChild(gradeIn);
        document.getElementById("gradeInSubmit").addEventListener("click", submitGrade, false);
    }
}
function submitGrade(cur) {
    console.log(curFormId);
    var getGrade = document.getElementById("gradeInBox").value;
    console.log(getGrade);
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
            var gradeButton = newRow.insertCell(7);
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
            gradeButton.innerHTML = "Click to input grade"
            gradeButton.style = "background:#99ccff";
            gradeButton.addEventListener("click", gradeInput, false);
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
                /*console.log("type: " + allForms[i].typeOfEvent);
                switch (allForms[i].status) {
                    case 0:
                        stat.innerHTML = "Avaiting Supervisor Approval";
                        break;
                    case 1:
                        stat.innerHTML = "Avaiting Department Approval";
                        break;
                    case 2:
                        stat.innerHTML = "Avaiting BenCo Approval";
                        break;
                    case 3:
                        stat.innerHTML = "Avaiting Grade";
                        break;
                    case 4:
                        stat.innerHTML = "Approved";
                        break;

                }*/
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