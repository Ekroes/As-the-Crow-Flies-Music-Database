<html>
<head>
<meta charset = "utf-8">
<title>As The Crow Flies Employee View</title>
</head>
<body>
	<div id = "wrapper">
		<div id ="header">
<h2>Welcome to As The Crow Flies Music Database</h2>
</div>
</div>

	<div id = "container">
	<div id = "content">
	
		<input type="button" value="Create new Employee"
				onclick="window.location.href = '/crowflies/users?command=addUserForm'; return false;"
				class="add-user-button" />
				<br><br>
	
		<a href = "/crowflies/users?command=logIn">Log In</a>
		
		<br><br>
		
		<a href = "/crowflies/users?command=userList"> View Employee Contact List</a>
	
	</div>	
	</div>




</body>
</html>
