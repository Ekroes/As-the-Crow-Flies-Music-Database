package com.github.ekroes.crowflies;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.security.auth.callback.ConfirmationCallback;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.omg.CORBA.Request;

import com.github.ekroes.crowflies.database.Driver;
import com.github.ekroes.crowflies.database.UserDAO;
import com.github.ekroes.crowflies.model.User;

/**
 * Servlet implementation class UserControllerServlet
 */
public class UserControllerServlet extends HttpServlet {
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
			theCommand = "userList";
		}

		if (theCommand.equals("userList")) {
			try {
				listUsers(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (theCommand.equals("loadUser")) {
			try {
				loadUserInfo(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (theCommand.equals("confirmDelete")) {
			comfirmDelete(request, response);
		}

		if (theCommand.equals("addUserForm")) {
			showAddForm(request, response);
		}

	if (theCommand.equals("logIn")) {
			showLoginForm(request, response);
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
			theCommand = "userList";
		}

		if (theCommand.equals("addUser")) {
			try {
				addUser(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (theCommand.equals("updateUser")) {
			try {
				updateUser(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (theCommand.equals("deleteUser")) {
			try {
				deleteUser(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

//		if (theCommand.equals("LogInAttempt")) {
//			try {
//				signIn(request, response);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}

	}

	private void comfirmDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/confirm-delete-user.jsp");
		dispatcher.forward(request, response);

	}

//	private void signIn(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, ServletException, IOException {
//		UserDAO dao = new UserDAO();
//		UserDAO signInDAO = new UserDAO();
//		String userName = request.getParameter("userName");
//		String password = request.getParameter("password");
//
//		dao.loginAttempt(userName, password);
//
//		boolean isSuccessful = signInDAO.loginAttempt(userName, password);
//		if (isSuccessful) {
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/list-artists.jsp");
//			dispatcher.forward(request, response);
//
//		} else {
//			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login-form.jsp");
//			dispatcher.forward(request, response);
//		}
//
//	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String userId = request.getParameter("userId");
		UserDAO dao = new UserDAO();
		dao.deleteUser(userId);
		listUsers(request, response);

	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("employeeId"));
		String userName = request.getParameter("userName");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		if(userName == null || "".equals(userName) || firstName == null || "".equals(firstName) ||
				lastName == null || "".equals(lastName) || email == null || "".equals(email)){
			loadUserInfo(request, response);
		}

		User theUser = new User(id, userName, firstName, lastName, email);
		UserDAO dao = new UserDAO();
		dao.updateUser(theUser);
		listUsers(request, response);

	}

	private void loadUserInfo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String userId = request.getParameter("employeeId");
		UserDAO dao = new UserDAO();
		User user = dao.getOneUser(userId);
		request.setAttribute("theUser", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/update-employees-form.jsp");
		dispatcher.forward(request, response);

	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		UserDAO dao = new UserDAO();
		List<User> users = dao.listUsers();

		request.setAttribute("userList", users);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/contact-list.jsp");
		dispatcher.forward(request, response);

	}

	private void addUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String userName = request.getParameter("userName");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(userName == null || "".equals(userName) || firstName == null || "".equals(firstName) ||
				lastName == null || "".equals(lastName) || email == null || "".equals(email) ||
				password == null || "".equals(password)){
			showAddForm(request, response);
		}

		User user = new User(userName, firstName, lastName, email, password);
		UserDAO dao = new UserDAO();
		dao.insertUser(user);

		listUsers(request, response);

	}

	private void showAddForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/add-employees-form.jsp");
		dispatcher.forward(request, response);

	}

	private void showLoginForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login-form.jsp");
		dispatcher.forward(request, response);

	}

}
