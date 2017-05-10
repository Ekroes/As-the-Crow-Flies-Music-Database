<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!Doctype html>
<html>
<head>
<title>Employee Users Tracker App</title>

<link type="text/CSS" rel="stylesheet" href="CSS/style.css">

</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>As the Crow Flies Music Database Employees</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">
		
		<table>
				<tr>
					
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					
				</tr>

				<c:forEach var="tempUser" items="${userList}">
				
				<tr>
				
						<td>${tempUser.firstName}</td>
						<td>${tempUser.lastName}</td>
						<td>${tempUser.email}</td>
						
						</tr>

				</c:forEach>

			</table>


		</div>
		
		<p>
		<a href = "/crowflies/">Return to Main Menu</a>
	</div>
						