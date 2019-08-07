
window.onload=function(){
	document.getElementsByClassName("btn btn-default")[0].onclick=login();
}

function login(){
	var un=document.getElementsByClassName("form-control")[0].value;
	var pw=document.getElementsByClassName("form-control")[1].value;
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){}
	xhr.open("POST", "/login", true);
	xhr.setRequestHeader("username", un);
	xhr.setRequestHeader("password", un);
	xhr.send();
}

function error(){
	window.alert("Username and password don't match!");
}