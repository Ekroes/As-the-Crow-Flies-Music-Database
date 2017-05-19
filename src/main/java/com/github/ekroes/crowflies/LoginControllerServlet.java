package com.github.ekroes.crowflies;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.ekroes.crowflies.database.LoginDAO;
import com.github.ekroes.crowflies.database.UserDAO;
import com.github.ekroes.crowflies.model.User;
import com.mysql.cj.api.log.Log;

/**
 * Servlet implementation class LoginControllerServlet
 */
public class LoginControllerServlet extends HttpServlet {
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

		if (theCommand.equals("logout")) {

			userLogout(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String theCommand = request.getParameter("command");

		if (theCommand.equals("validateLogIn")) {

			try {
				validateLogIn(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private void validateLogIn(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		//String errorMessage = "";
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		int userId = Integer.parseInt(request.getParameter("ID"));

		LoginDAO loginDAO = new LoginDAO();

		if (loginDAO.loginAttempt(userName, password)) {

			User validUser = loginDAO.getUser(userId);
			HttpSession session = request.getSession();
			session.setAttribute("theUser", validUser);

			response.sendRedirect("/artist");
			
//		}else {
//			try{
//				LoginDAO.incrementLoginAttempts(userId);
//			}
//			finally{
//				errorMessage = "User Name or Password invalid. Please try again";
//			}
		}

	}

	private void userLogout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();

		RequestDispatcher dispatcher = request.getRequestDispatcher("/crowflies");
		dispatcher.forward(request, response);

	}

}
