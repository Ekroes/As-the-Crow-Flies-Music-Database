<!Doctype html>
<html>
<head>

</head>

<title>Update Album</title>
<link type = "text/css" rel = "stylesheet" href = "CSS/style.css"/>
<link type = "text/css" rel = "stylesheet" href = "CSS/add-employee-style.css"/>
<body>
<div id = "wrapper">
<div id = "header">
	<h2>As the Crow Flies Music Database</h2>
</div>
</div>

<div id = "container">
<h3>Update Album</h3>

<form action = "/crowflies/album" method = "POST">
	<input type = "hidden" name = "command" value = "updateAlbum"/>
	<input type = "hidden" name = "albumId" value = "${theAlbum.albumId}"/>
	<input type = "hidden" name ="Artist_ID" value = "${theAlbum.artistId}"/>
	
	<table>
	<tbody>
		<tr>
		<td><label>Album Name:</label></td>
		<td><input type = "text" name = "name" value = "${theAlbum.name}"/>
		</tr>
		<tr>
		<td><label>Year Released:</label></td>
		<td><input type = "text" name = "releaseDate" value ="${theAlbum.releaseDate}"/>
		</tr>
		<tr>
		<td><label></label></td>
		<td><input type = "submit" value = "Save" class = "save"/>
	
	</tbody>
	</table>

</form>

<div style = "clear: both;"></div>
<p>
<a href = "/crowflies/album?command=listAlbums&Artist_ID=${artistId}">Back to List</a>
</div>
</body>
</html>