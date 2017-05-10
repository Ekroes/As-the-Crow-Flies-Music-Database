package com.github.ekroes.crowflies;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.ekroes.crowflies.database.ArtistDAO;
import com.github.ekroes.crowflies.model.Artist;

/**
 * Servlet implementation class ArtistControllerServlet
 */
public class ArtistControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
	}

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

		if (theCommand.equals("artistList")) {
			try {
				listArtists(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (theCommand.equals("loadArtist")) {
			try {
				loadArtistInfo(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (theCommand.equals("addArtistForm")) {

			showAddForm(request, response);

		}

		if (theCommand.equals("confirmDelete")) {

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

		if (theCommand.equals("addArtist")) {
			try {
				addArtist(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (theCommand.equals("updateArtist")) {
			try {
				updateArtist(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (theCommand.equals("deleteArtist")) {
			try {
				deleteArtist(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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

		if (artistName == null || "".equals(artistName) || careerStart == null || "".equals(careerStart)) {
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
		if (artistName == null || "".equals(artistName) || careerStart == null || "".equals(careerStart)) {
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
		ArtistDAO dao = new ArtistDAO();
		List<Artist> artists = dao.listArtists();

		request.setAttribute("artistList", artists);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/list-artists.jsp");
		dispatcher.forward(request, response);

	}

	private void loadArtistInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		String artistId = request.getParameter("Artist_ID");
		ArtistDAO dao = new ArtistDAO();
		Artist artist = dao.getOneArtist(artistId);
		request.setAttribute("theArtist", artist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/update-artist-form.jsp");
		dispatcher.forward(request, response);
	}

	private void confirmDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/confirm-delete-artist.jsp");
		dispatcher.forward(request, response);

	}

	private void showAddForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/add-artist-form.jsp");
		dispatcher.forward(request, response);

	}

}
