
<!DOCTYPE html>
<html>

<head>


</head>

<title>Update Employee</title>
<link type = "text/css" rel = "stylesheet" href = "CSS/general-style.css"/>
<link type = "text/css" rel = "stylesheet" href = "CSS/add-employee-style.css"/>
<body>
	<div id = "wrapper">
		<div id = "header">
			<h2>As the Crow Flies Music Database</h2>
		</div>
	
	</div>


	<div id = "container">
		<h3>Update Employee</h3>
		
		<form action = "/crowflies/users" method = "POST">
			<input type = "hidden" name = "command" value = "updateUser"/>
			<input type = "hidden" name = "employeeId" value = "${theUser.id}"/>
			
			<table>
			<tbody>
				<tr>
				<td><label>User Name:</label></td>
				<td><input type = "text" name = "userName" value = "${theUser.userName}"/>
				</tr>
				<tr>
				<td><label>First Name:</label></td>
				<td><input type = "text" name = "firstName" value = "${theUser.firstName}"/>
				</tr>
				<tr>
				<td><label>Last Name:</label></td>
				<td><input type = "text" name = "lastName" value = "${theUser.lastName}"/>
				</tr>
				<tr>
				<td><label>Email:</label></td>
				<td><input type = "text" name = "email" value = "${theUser.email}"/>
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