<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!Doctype html>
<html>
<head>
<title>Employee Users Tracker App</title>

<link type="text/CSS" rel="stylesheet" href="CSS/general-style.css">

</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>As the Crow Flies Music Database Employees</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<input type="button" value="Create new Employee"
				onclick="window.location.href = '/crowflies/users?command=addUserForm'; return false;"
				class="add-user-button" />


			<table>
				<tr>
					<th>User Name</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>

				<c:forEach var="tempUser" items="${userList}">
				
					

					<c:url var="tempLink" value="/users">
						<c:param name="command" value="loadUser" />
						<c:param name="employeeId" value="${tempUser.getId()}" />
					</c:url>

					<c:url var="deleteLink" value="/users">
						<c:param name="command" value="confirmDelete" />
						<c:param name="employeeId" value="${tempUser.getId()}" />
					</c:url>


					<tr>

						<td>${tempUser.userName}</td>
						<td>${tempUser.firstName}</td>
						<td>${tempUser.lastName}</td>
						<td>${tempUser.email}</td>
						<td><a href="${tempLink}">Update</a> | 
						<a href="${deleteLink}">
						Delete</a>
						</td>
					</tr>

				</c:forEach>

			</table>


		</div>
		
		<p>
		<a href = "/crowflies/">Return to Main Menu</a>
	</div>


</body>
</html>