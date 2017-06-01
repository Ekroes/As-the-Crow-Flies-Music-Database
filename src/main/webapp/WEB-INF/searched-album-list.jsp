<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>

<html>
<head>
<title>Album Tracker App</title>

<link type="text/CSS" rel="stylesheet" href="CSS/general-style.css">
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>${artistName.name}'sAlbums</h2>

		</div>

	</div>

	<div id="container">
		<div id="content">
			
			<table>
				<tr>
					<th>Album Name</th>
					<th>Year Released</th>
					
				</tr>
				<c:forEach var="tempAlbum" items="${listAlbums}">




					<tr>
						<td>${tempAlbum.name}</td>
						<td>${tempAlbum.releaseDate}</td>

					</tr>

				</c:forEach>

			</table>

		</div>

		<div style="clear: both;"></div>
		<p>
			<a href="/crowflies">Back to Main Menu </a>
		</p>

	</div>

</body>
</html>