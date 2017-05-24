
<!DOCTYPE html>
<html>

<head>


</head>

<title>Add New Employee</title>
<link type = "text/css" rel = "stylesheet" href = "CSS/general-style.css"/>
<link type = "text/css" rel = "stylesheet" href = "CSS/add-user-style.css"/>
<body>
	<div id = "wrapper">
		<div id = "header">
			<h2>As the Crow Flies Music Database</h2>
		</div>
	
	</div>


	<div id = "container">
		<h3>Create New Employee</h3>
		
		<form action = "/crowflies/users" method = "POST">
			<input type = "hidden" name = "command" value = "addUser"/>
			
			<table>
			<tbody>
				<tr>
				<td><label>User Name:</label></td>
				<td><input type = "text" name = "userName"/>
				</tr>
				<tr>
				<td><label>First Name:</label></td>
				<td><input type = "text" name = "firstName"/>
				</tr>
				<tr>
				<td><label>Last Name:</label></td>
				<td><input type = "text" name = "lastName"/>
				</tr>
				<tr>
				<td><label>Email:</label></td>
				<td><input type = "text" name = "email"/>
				</tr>
				<tr>
				<td><label>Password:</label></td>
				<td><input type = "text" name = "password"/>
				</tr>
				<tr>
				<td><label></label></td>
				<td><input type = "submit" value = "Save" class = "save"/>
				</tr>
			</tbody>
			</table>
		</form>
		
		<div style = "clear: both;"></div>
		<p>
			<a href = "/crowflies/users?command=userList">Back to List</a>
		</p>
	</div>

</body>

</html>