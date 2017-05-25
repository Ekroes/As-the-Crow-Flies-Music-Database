package com.github.ekroes.crowflies;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.ekroes.crowflies.database.AlbumDAO;
import com.github.ekroes.crowflies.database.ArtistDAO;
import com.github.ekroes.crowflies.model.Album;
import com.github.ekroes.crowflies.model.Artist;

import util.Validation;

/**
 * Servlet implementation class AlbumControllerServlet
 */
public class AlbumControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String theCommand = request.getParameter("command");

		if (theCommand == null) {
			theCommand = "listAlbums";
		}

		if ("listAlbums".equals(theCommand)) {
			try {
				listAlbums(request, response);
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

		if ("loadAlbum".equals(theCommand)) {
			try {
				loadAlbumInfo(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		if ("addAlbumForm".equals(theCommand)) {
			showAddForm(request, response);
		}

		if ("confirmDelete".equals(theCommand)) {
			confirmDelete(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String theCommand = request.getParameter("command");

		if ("addAlbum".equals(theCommand)) {
			try {
				addAlbum(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if ("updateAlbum".equals(theCommand)) {
			try {
				updateAlbum(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if ("deleteAlbum".equals(theCommand)) {
			try {
				deleteAlbum(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void deleteAlbum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String albumId = request.getParameter("Album_ID");
		AlbumDAO dao = new AlbumDAO();
		dao.deleteAlbum(albumId);
		listAlbums(request, response);

	}

	private void updateAlbum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int albumId = Integer.parseInt(request.getParameter("albumId"));
		int artistId = Integer.parseInt(request.getParameter("Artist_ID"));
		String albumName = request.getParameter("name");
		String releaseDate = request.getParameter("releaseDate");

		if (Validation.isNullOrEmpty(albumName) || Validation.isNullOrEmpty(releaseDate)) {
			loadAlbumInfo(request, response);
		} else {
			Album modifiedAlbum = new Album(albumId, albumName, releaseDate, artistId);
			AlbumDAO dao = new AlbumDAO();
			dao.updateArtist(modifiedAlbum);
			listAlbums(request, response);
		}
	}

	private void addAlbum(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String albumName = request.getParameter("name");
		String releaseDate = request.getParameter("releaseDate");
		String artistIds = request.getParameter("Artist_ID");

		Integer artistId = Integer.parseInt(artistIds);

		if (Validation.isNullOrEmpty(albumName) || Validation.isNullOrEmpty(releaseDate)) {
			showAddForm(request, response);
		}

		Album newAlbumInfo = new Album(albumName, releaseDate, artistId);
		AlbumDAO dao = new AlbumDAO();
		dao.insertAlbum(newAlbumInfo);

		listAlbums(request, response);
	}

	private void listAlbums(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String artistId = request.getParameter("Artist_ID");

		AlbumDAO dao = new AlbumDAO();
		ArtistDAO artDAO = new ArtistDAO();
		List<Album> albums = dao.listAlbums(artistId);
		Artist artistName = artDAO.getArtistName(artistId);

		request.setAttribute("artistId", artistId);

		request.setAttribute("artistName", artistName);

		request.setAttribute("listAlbums", albums);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/list-albums.jsp");
		dispatcher.forward(request, response);

	}

	private void loadAlbumInfo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String albumId = request.getParameter("Album_ID");
		String artistId = request.getParameter("Artist_ID");
		AlbumDAO dao = new AlbumDAO();
		Album album = dao.getOneAlbum(albumId, artistId);
		request.setAttribute("theAlbum", album);
		request.setAttribute("artistId", artistId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/update-album-form.jsp");
		dispatcher.forward(request, response);
	}

	private void confirmDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String albumId = request.getParameter("Album_ID");
		String artistId = request.getParameter("Artist_ID");
		request.setAttribute("albumId", albumId);
		request.setAttribute("artistId", artistId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/confirm-delete-album.jsp");
		dispatcher.forward(request, response);
	}

	private void showAddForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String artistId = request.getParameter("Artist_ID");
		request.setAttribute("artistId", artistId);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/add-album-form.jsp");
		dispatcher.forward(request, response);
	}

}
