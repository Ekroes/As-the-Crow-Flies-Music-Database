<!Doctype HTML>

<html>
<head>

<title>Delete Album</title>

</head>

<link type = "text/css" rel = "stylesheet" href = "CSS/style.css"/>
<link type = "text/css" rel = "stylesheet" href ="CSS/delete-album-style.css"/>

<body>
	<div id = "wrapper">
		<div id = "header">
		
			<h2>As The Crow Flies Music Database</h2>
	</div>
	</div>
	
	<div id = "container">
		<h3>Delete the Album?</h3>
		
		<form action = "/crowflies/album" method = "POST">
			<input type = "hidden" name = "command" value = "deleteAlbum"/>
			<input type = "hidden" name = "Album_ID" value = "${albumId}"/>
			<input type = "submit" value = "Yes" class = "save" />
		
		</form>
		
		<div style = "clear:both;"></div>
		
		<p>
			<a href ="/crowflies/album?command=listAlbums&Artist_ID=${artistId}">Back to Album List </a>
			
			</p>
			
	</div>
</body>

</html>