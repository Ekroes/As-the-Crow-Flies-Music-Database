

<!DOCTYPE html>
<html>

<head>


</head>

<title>Update Artist</title>
<link type = "text/css" rel = "stylesheet" href = "CSS/style.css"/>
<link type = "text/css" rel = "stylesheet" href = "CSS/add-employee-style.css"/>
<body>
	<div id = "wrapper">
		<div id = "header">
			<h2>As the Crow Flies Music Database</h2>
		</div>
	
	</div>


	<div id = "container">
		<h3>Update Artist</h3>
		
		<form action = "/crowflies/artist" method = "POST">
			<input type = "hidden" name = "command" value = "updateArtist"/>
			<input type = "hidden" name = "Artist_ID" value = "${theArtist.id}"/>
			
			<table>
			<tbody>
				<tr>
				<td><label>Artist Name:</label></td>
				<td><input type = "text" name = "name" value = "${theArtist.name}"/>
				</tr>
				<tr>
				<td><label>Start of Career:</label></td>
				<td><input type = "text" name = "start" value = "${theArtist.start}"/>
				</tr>
				<tr>
				<td><label>End of Career:</label></td>
				<td><input type = "text" name = "end" value = "${theArtist.end}"/>
				</tr>
				<tr>
				<td><label></label></td>
				<td><input type = "submit" value = "Save" class = "save"/>
				</tr>
			</tbody>
			</table>
		</form>
		
		<div style = "clear: both;"></div>
		<p>
			<a href = "/crowflies/artist?command=artistList">Back to List</a>
		</p>
	</div>

</body>

</html>