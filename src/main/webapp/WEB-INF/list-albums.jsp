<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>

<html>
<head>
<title>Album Tracker App</title>

<link type ="text/CSS" rel="stylesheet" href="CSS/style.css">
</head>

<body>
	<div id = "wrapper">
	<div id = "header">
			<h2>${artistName.name}'s Albums</h2>
	
	</div>
	
	</div>

	<div id = "container">
	<div id = "content">
		<input type = "button" value = "Add a New Album"
			onclick = "window.location.href = '/crowflies/album?command=addAlbumForm&Artist_ID= ${artistId}'; return false;"
			class="add-album-button"/>
			
			<table>
				<tr>
					<th>Album Name</th>
					<th>Year Released</th>
				</tr>
				<c:forEach var = "tempAlbum" items="${listAlbums}">
				
					<c:url var = "tempLink" value="/album">
						<c:param name = "command" value = "loadAlbum" />
						<c:param name="Album_ID" value="${tempAlbum.getAlbumId()}"/>
						<c:param name = "Artist_ID" value="${tempAlbum.getArtistId()}"/>
						</c:url>
						
					<c:url var = "deleteLink" value ="/album">
						<c:param name ="command" value= "confirmDelete" />
						<c:param name ="Album_ID" value = "${tempAlbum.getAlbumId()}"/>
						<c:param name = "Artist_ID" value = "${tempAlbum.getArtistId()}"/>
					</c:url>
					
					<tr>
						<td>${tempAlbum.name}</td>
						<td>${tempAlbum.releaseDate}</td>
						<td><a href="${tempLink}">Update</a> |
						<a href="${deleteLink}">Delete</a>
						</td>
				</tr>
				
				</c:forEach>
			
			</table>
	
	</div>
	
	<div style = "clear: both;"></div>
	<p>
	<a href = "/crowflies/artist?command=artistList">Back to Artist List</a>
	</p>
	
	</div>

</body>
</html>