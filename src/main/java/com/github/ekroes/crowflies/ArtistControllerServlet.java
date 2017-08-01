package com.github.ekroes.crowflies;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.ekroes.crowflies.database.ArtistDAO;
import com.github.ekroes.crowflies.model.Artist;
import com.github.ekroes.crowflies.model.User;

import util.Validation;

/**
 * Servlet implementation class ArtistControllerServlet
 */
public class ArtistControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String theCommand = request.getParameter("command");

		if (theCommand == null) {
			theCommand = "artistList";
		}

		if ("artistList".equals(theCommand)) {
			try {
				listArtists(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if ("loadArtist".equals(theCommand)) {
			try {
				loadArtistInfo(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if ("searchArtist".equals(theCommand)) {
			try {
				searchArtistByName(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if ("addArtistForm".equals(theCommand)) {
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

		if (theCommand == null) {
			theCommand = "artistList";
		}

		if ("addArtist".equals(theCommand)) {
			try {
				addArtist(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if ("updateArtist".equals(theCommand)) {
			try {
				updateArtist(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if ("deleteArtist".equals(theCommand)) {
			try {
				deleteArtist(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void searchArtistByName(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

		String artistName = request.getParameter("artistName");
		ArtistDAO dao = new ArtistDAO();
		
		List<Artist> artists = dao.searchForArtist(artistName);
		request.setAttribute("searchResults", artists);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/artist-search-results.jsp");
		dispatcher.forward(request, response);
		

	}

	private void deleteArtist(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String artistId = request.getParameter("Artist_ID");
		ArtistDAO dao = new ArtistDAO();
		request.setAttribute("theArtist", artistId);
		dao.deleteArtist(artistId);
		listArtists(request, response);
	}

	private void updateArtist(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("Artist_ID"));
		String artistName = request.getParameter("name");
		String careerStart = request.getParameter("start");
		String careerEnd = request.getParameter("end");

		if (Validation.isNullOrEmpty(artistName) || Validation.isNullOrEmpty(careerStart)) {
			loadArtistInfo(request, response);
		}

		Artist theArtist = new Artist(id, artistName, careerStart, careerEnd);
		ArtistDAO dao = new ArtistDAO();
		dao.updateArtist(theArtist);
		listArtists(request, response);
	}

	private void addArtist(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String artistName = request.getParameter("name");
		String careerStart = request.getParameter("start");
		String careerEnd = request.getParameter("end");
		if (Validation.isNullOrEmpty(artistName) || Validation.isNullOrEmpty(careerStart)) {
			// out.print("Please fill in the fields appropriately");
			showAddForm(request, response);
		}

		Artist anArtist = new Artist(artistName, careerStart, careerEnd);
		ArtistDAO dao = new ArtistDAO();
		dao.insertArtist(anArtist);

		listArtists(request, response);
	}

	private void listArtists(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		User validUser = (User) request.getSession().getAttribute("theUser");
		ArtistDAO dao = new ArtistDAO();
		List<Artist> artists = dao.listArtists();

		request.setAttribute("artistList", artists);
		request.setAttribute("userRole", validUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/list-artists.jsp");
		dispatcher.forward(request, response);
	}

	private void loadArtistInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String artistId = request.getParameter("Artist_ID");
		ArtistDAO dao = new ArtistDAO();
		Artist artist = dao.getOneArtist(artistId);
		request.setAttribute("theArtist", artist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/update-artist-form.jsp");
		dispatcher.forward(request, response);
	}

	private void confirmDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String artistId = request.getParameter("Artist_ID");
		request.setAttribute("artistId", artistId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/confirm-delete-artist.jsp");
		dispatcher.forward(request, response);
	}

	private void showAddForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/add-artist-form.jsp");
		dispatcher.forward(request, response);
	}

}
