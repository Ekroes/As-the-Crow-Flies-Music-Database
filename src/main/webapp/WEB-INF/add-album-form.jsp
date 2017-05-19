<!Doctype html>

<html>
<head>

</head>

<title>Add New Album</title>

<link type ="text/css" rel = "stylesheet" href = "CSS/general-style.css"/>
<link type = "text/css" rel = "stylesheet" href = "CSS/add-album-style.css"/>

<body>
	<div id = "wrapper">
	<div id = "header">
		<h2>As the Crow Flies Music Database</h2>
	</div>
	</div>
	
	<div id = "container">
		<h3>Add New Album</h3>
		
		<form action="/crowflies/album" method = "POST">
			<input type = "hidden" name = "command" value ="addAlbum"/>
			<input type = "hidden" name = "Artist_ID" value = "${artistId}"/>			
			<table>
			<tbody>
			<tr>
			<td><label>Album Name:</label></td>
			<td><input type = "text" name = "name"/>
			</tr>
			<tr>
			<td><label>Year Released:</label></td>
			<td><input type = "text" name = "releaseDate"/>
			</tr>
			<tr>
			<td><input type = "submit" value = "Save" class = "save"/>
			</td>
			</tr>
			</tbody>
			</table>
		</form>
		
		<div style = "clear: both;"></div>
		<p>
			<a href = "/crowflies/album?command=listAlbums&Artist_ID=${artistId}">Back to List</a></p>
	</div>

</body>
</html>