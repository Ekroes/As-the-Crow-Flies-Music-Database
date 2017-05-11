<!Doctype html>

<html>
<head>
<title>Delete Artist</title>
</head>



<link type="text/css" rel="stylesheet" href="CSS/style.css" />
<link type="text/css" rel="stylesheet"
	href="CSS/delete-artist-style.css" />

<body>
	<div id="wrapper">
		<div id="header">
			<h2>As the Crow Flies Music Database</h2>
		</div>
	</div>

	<div id="container">
		<h3>Delete the Artist?</h3>

		<form action="/crowflies/artist" method="POST">
			<input type="hidden" name="command" value="deleteArtist" /> 
			<input type = "hidden" name = "Artist_ID" value ="${artistId}"/>
			<input type="submit" value="Yes" class="save" />

		</form>


		<div style="clear: both;"></div>

		<p>
			<a href="/crowflies/artist?command=artistList">Back to List</a>
		</p>
	</div>

</body>
</html>