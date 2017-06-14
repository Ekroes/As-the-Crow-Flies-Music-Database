package com.github.ekroes.crowflies.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.github.ekroes.crowflies.model.User;

public class LoginDAO {
	
	public boolean loginAttempt(String userName, String password) throws SQLException {
		String sql = "SELECT * FROM useraccount WHERE UserName = ? AND login = ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		DataSource dataSource = Driver.getDataSource();
		
		
		try{
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			res = pstmt.executeQuery();
			
			return res.next();
		}finally{
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
			Driver.closeResultSet(res);
		}
		
	}

	public User getUser(int userId) throws SQLException {

		User theUser = null;
		
		String sql = "SELECT * FROM useraccount WHERE ID = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		DataSource dataSource = Driver.getDataSource();
		
		try{
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			res = pstmt.executeQuery();
			
			if(res.next()){
				int id = res.getInt("ID");
				String userName = res.getString("UserName");
				String firstName = res.getString("FirstName");
				String lastName = res.getString("LastName");
				String password = res.getString("Login");
				String email = res.getString("Email");
				String role = res.getString("Role");
				
				theUser = new User(id, userName, firstName, lastName, password, email, role);
			}
			
			else{
				 throw new SQLException("Could not find user " + userId);
			}
			return theUser;
			
		} finally {
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
			Driver.closeResultSet(res);
		}
		
		
	}

	

//	public static void incrementLoginAttempts(int userId) throws SQLException {
//
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		DataSource dataSource = Driver.getDataSource();
//		
//		try{
//			conn = dataSource.getConnection();
//			
//		} finally {
//			Driver.closeConnection(conn);
//			Driver.closePreparedStatement(pstmt);
//		}
//		
//	}

}
