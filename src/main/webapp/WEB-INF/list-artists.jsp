<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!Doctype html>

<html>
<head>
<title>Artist Tracker App</title>

<link type="text/CSS" rel="stylesheet" href="CSS/style.css">

</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>As The Crow Flies Music Database</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">

			<input type="button" value="Add new Artist"
				onclick="window.location.href = '/crowflies/artist?command=addArtistForm'; return false;"
				class="add-artist-button" />




			<table>
				<tr>
					<th>Name</th>
					<th>Career Start</th>
					<th>Career End</th>
					<th>Action</th>
				</tr>

				<c:forEach var="tempArtist" items="${artistList}">
				
					<c:url var="albumLink" value="/album">
						<c:param name="command" value="listAlbums" />
						<c:param name="Artist_ID" value="${tempArtist.getId()}" />
					</c:url>
					
					<c:url var="tempLink" value="/artist">
						<c:param name="command" value="loadArtist" />
						<c:param name="Artist_ID" value="${tempArtist.getId()}" />
					</c:url>

					<c:url var="deleteLink" value="/artist">
						<c:param name="command" value="confirmDelete" />
						<c:param name="Artist_ID" value="${tempArtist.getId()}" />
					</c:url>

					<tr>

						<td><a href ="${albumLink}">${tempArtist.name}</a></td>
						<td>${tempArtist.start}</td>
						<td>${tempArtist.end}</td>
						<td><a href="${tempLink}">Update</a>| <a href="${deleteLink}">
								Delete</a></td>
					</tr>


				</c:forEach>
			</table>

		</div>

	</div>

</body>
</html>