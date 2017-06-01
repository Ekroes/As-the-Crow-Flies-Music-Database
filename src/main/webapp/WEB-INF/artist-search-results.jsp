<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!Doctype html>

<html>
<head>
<title>Artist Tracker App</title>

<link type="text/CSS" rel="stylesheet" href="CSS/general-style.css">

</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>As The Crow Flies Music Database</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">

			




			<table>
				<tr>
					<th>Name</th>
					<th>Career Start</th>
					<th>Career End</th>
					
				</tr>

				<c:forEach var="tempArtist" items="${searchResults}">
				
					<c:url var="albumLink" value="/album">
						<c:param name="command" value="listAlbumsAfterSearch" />
						<c:param name="Artist_ID" value="${tempArtist.getId()}" />
					</c:url>
					
					

					<tr>

						<td><a href ="${albumLink}">${tempArtist.name}</a></td>
						<td>${tempArtist.start}</td>
						<td>${tempArtist.end}</td>
						
					</tr>


				</c:forEach>
			</table>

		</div>

	</div>

</body>
</html>