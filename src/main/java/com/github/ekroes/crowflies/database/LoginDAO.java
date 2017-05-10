package com.github.ekroes.crowflies.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

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
			if(res.next()){
				return true;
			}else{
				return false;
			}
		}finally{
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
			Driver.closeResultSet(res);
		}
		
	}

}
