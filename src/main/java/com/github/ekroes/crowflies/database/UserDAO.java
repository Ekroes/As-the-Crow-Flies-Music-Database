package com.github.ekroes.crowflies.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import com.github.ekroes.crowflies.model.User;

public class UserDAO {

	public List<User> listUsers() throws SQLException {
		List<User> users = new ArrayList<User>();
		DataSource dataSource = Driver.getDataSource();

		String sql = "SELECT * FROM useraccount " + "ORDER BY ID";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();

			while (res.next()) {
				users.add(new User(res.getInt("ID"), res.getString("UserName"), res.getString("FirstName"),
						res.getString("LastName"), res.getString("Email"), res.getString("Role")));

			}
			return users;
		} finally {
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
			Driver.closeResultSet(res);
		}

	}

	public void insertUser(User newUserInfo) throws SQLException {
		String sql = "INSERT INTO useraccount (UserName, FirstName, LastName, Login, Email) VALUES (?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		int id = 0;

		DataSource dataSource = Driver.getDataSource();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, newUserInfo.getUserName());
			pstmt.setString(2, newUserInfo.getFirstName());
			pstmt.setString(3, newUserInfo.getLastName());
			pstmt.setString(5, newUserInfo.getPassword());
			pstmt.setString(4, newUserInfo.getEmail());
			pstmt.executeUpdate();
			res = pstmt.getGeneratedKeys();

			if (res.next()) {
				id = res.getInt(1);
				newUserInfo.setId(id);
			}
		} finally {
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
			Driver.closeResultSet(res);
		}
	}

	public User getOneUser(String userId) throws SQLException {
		User aUser = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		int id;
		String sql = "SELECT * FROM useraccount WHERE ID = ?";

		DataSource dataSource = Driver.getDataSource();
		try {
			id = Integer.parseInt(userId);
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			res = pstmt.executeQuery();
			if (res.next()) {
				aUser = new User(res.getInt("ID"), res.getString("UserName"), res.getString("FirstName"),
						res.getString("LastName"), res.getString("Email"), res.getString("Role"));
			}
			return aUser;
		} finally {
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
			Driver.closeResultSet(res);
		}

	}

	public void updateUser(User theUser) throws SQLException {

		String sql = "UPDATE useraccount SET UserName = ?, FirstName = ?," + " LastName = ?, Email = ? WHERE ID = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		DataSource dataSource = Driver.getDataSource();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, theUser.getUserName());
			pstmt.setString(2, theUser.getFirstName());
			pstmt.setString(3, theUser.getLastName());
			pstmt.setString(4, theUser.getEmail());
			pstmt.setInt(5, theUser.getId());
			pstmt.executeUpdate();

		} finally {
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
			Driver.closeResultSet(null);

		}

	}

	public void deleteUser(String userId) throws SQLException {
		String sql = "DELETE FROM useraccount WHERE ID = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		int id;

		DataSource dataSource = Driver.getDataSource();
		try {
			id = Integer.parseInt(userId);
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} finally {
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
		}
	}

	public User getUserByUserName(String userName) throws SQLException {
		User aUser = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		String sql = "SELECT * FROM useraccount WHERE UserName = ?";

		DataSource dataSource = Driver.getDataSource();
		try {
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			res = pstmt.executeQuery();
			if (res.next()) {
				aUser = new User(res.getInt("ID"), res.getString("UserName"), res.getString("FirstName"),
						res.getString("LastName"), res.getString("Email"), res.getString("Role"));
			}
			return aUser;
		} finally {
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
			Driver.closeResultSet(res);
		}

	}
		
	

	

}
