function logout() {
	// Step 1
	var xhr = new XMLHttpRequest();
	// Step 2 function to handle onreadystatechange of response
	xhr.onreadystatechange = function() {
		console.log(" ");
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log('Logging out');
		} else {
			console.log("Failed to logout");
		}
	}
	// Step 3 open the request/connection
	xhr.open("GET", "/logout", true);
	// Step 4 send the request
	xhr.send();
}

function home() {
	// Step 1
	var xhr = new XMLHttpRequest();
	// Step 2 function to handle onreadystatechange of response
	xhr.onreadystatechange = function() {
		console.log(" ");
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log('Redirecting to home...');
		} else {
			console.log("Failed to redirect!");
		}
	}
	// Step 3 open the request/connection
	xhr.open("GET", "/return", true);
	// Step 4 send the request
	xhr.send();
}

function form() {
	// Step 1
	var xhr = new XMLHttpRequest();
	// Step 2 function to handle onreadystatechange of response
	xhr.onreadystatechange = function() {
		console.log(" ");
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log(''); 
		} else {
			console.log("");
		}
	}
	// Step 3 open the request/connection
	xhr.open("GET", "htf", true);
	// Step 4 send the request
	xhr.send();
}