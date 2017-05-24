<!Doctype html>

<html>
<head>

</head>

<title>Delete User</title>

<link type="text/css" rel="stylesheet" href="CSS/general-style.css" />
<link type="text/css" rel="stylesheet" href="CSS/add-user-style.css" />

<body>
	<div id="wrapper">
		<div id="header">
			<h2>As the Crow Flies Music Database</h2>
		</div>
	</div>

	<div id="container">
		<h3>Delete the Account?</h3>

		<form action="crowflies/users" method="POST">
			<input type="hidden" name="command" value="deleteUser" /> 
			<input type ="hidden" name ="User_ID" value = "${userId}"/>
			<input type="submit" value="Yes" class="save" />

		</form>


		<div style="clear: both;"></div>

		<p>
			<a href="/crowflies/users?command=userList">Back to List</a>
		</p>
	</div>

</body>
</html>