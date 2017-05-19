<!Doctype html>

<html>
<head>
<title>Login Form</title>
</head>

<link type = "text/css" rel = "stylesheet" href = "CSS/general-style.css"/>

<body>
	<div id = "wrapper">
		<div id ="header">
		<h1>As The Crow Flies Music Database</h1>
	</div>
	</div>
	
	<div id = "container">
		<h2>Please Log In</h2>
		
		<form action = "/crowflies/login" method = "POST">
			<input type = "hidden" name = "command" value = "validateLogIn"/>
			<input type = "hidden" name = "userName" value = "${userName}"/>
			<input type = "hidden" name ="password" value = "${password}"/>
		
		
			<table>
			<tbody>
				<tr>
				<td><label>User Name:</label></td>
				<td><input type = "text" name = "userName"/>
				</tr>
				<tr>
				<td><label>Password:</label></td>
				<td><input type ="password" name = "password"/>
				</tr>
				<tr>
				<td><label></label></td>
				<td><input type = "submit" value = "Enter" class = "save"/>
				</tr>
		
			</tbody>
			</table>
		</form>
		
		<div style = "clear: both;"></div>
		<a href = "/crowflies/"> Return to Main Menu</a>
	</div>

</body>
</html>