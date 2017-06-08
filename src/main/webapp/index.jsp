<html>
<head>
<meta charset="utf-8">
<title>As The Crow Flies Music Database Home Page</title>

<link type="text/css" rel="stylesheet" href="CSS/general-style.css" />
<link type="text/css" rel="stylesheet" href="CSS/add-album-style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Welcome to As The Crow Flies Music Database</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">

			<input type="button" value="Create new Employee"
				onclick="window.location.href = '/crowflies/users?command=addUserForm'; return false;"
				class="add-user-button" /> <br> <br>

			<div id="search">
				<form action="/crowflies/artist" method="GET">
					<input type="hidden" name="command" value="searchArtist" />
					<table>
						<tbody>
							<tr>
								<td><label>Search by Artist:</label></td>
								<td><input type="text" name="artistName" /></td>
							</tr>
							<tr>
								<td colspan=2><input type="submit" value="Search"
									class="search-artist-button"></td>
							</tr>
						</tbody>
					</table>
				</form>
			<!--  	<form action="/crowflies/album" method="GET">
					<input type="hidden" name="command" value="searchAlbum" />
					<table>
						<tbody>
							<tr>
								<td><label> Search by Album:</label></td>
								<td><input type="text" name="albumName"> </td></tr>
									<tr>
									<td colspan=2> <input type="submit" value="Search" class = "search-album-button"></td></tr>
						</tbody>
					</table>
				</form>-->
			</div>

			<a href="/crowflies/users?command=logIn">Log In</a> <a
				href="/crowflies/users?command=userList"> View Employee Contact
				List</a>

		</div>
	</div>




</body>
</html>
