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
					<th>Album Name</th>
					<th>Year Released</th>
					
					
				</tr>

				<c:forEach var="tempAlbum" items="${searchResults}">
				
					<c:url var="artistLink" value="/artist">
						<c:param name="command" value="listArtistAfterSearch" />
						<c:param name="Artist_ID" value="${tempAlbum.getArtistId()}" />
					</c:url>
					
					

					<tr>

						<td><a href ="${artistLink}">${tempArtist.name}</a></td>
						<td>${tempAlbum.yearReleased}</td>
						
						
					</tr>


				</c:forEach>
			</table>

		</div>
		
		<p>
		<a href ="/crowflies"> Back to Main Menu </a>

	</div>

</body>
</html>