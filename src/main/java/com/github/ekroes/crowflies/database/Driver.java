package com.github.ekroes.crowflies.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class Driver {


	public static DataSource getDataSource() {
		PropertyResourceBundle dbProps = (PropertyResourceBundle) ResourceBundle.getBundle("db");
		if ("".equals(dbProps.getString("db.username"))) {
			throw new RuntimeException("Can't understand contents of db.properties file - got empty username.");
		}		
		
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(dbProps.getString("db.driver"));
		ds.setUsername(dbProps.getString("db.username"));
		ds.setPassword(dbProps.getString("db.password"));

		// some versions of the mysql driver have a bug in timezone detection;
		// this is a workaround.
		String timezoneWorkaround = "&useLegacyDatetimeCode=false&serverTimezone=UTC";

		// general settings to make it more cooperative
		String config = "?useUnicode=true&useSSL=false";

		ds.setUrl(dbProps.getString("db.url") + dbProps.getString("db.schema") + config + timezoneWorkaround);

		// the settings below are optional -- dbcp can work with defaults
		ds.setMinIdle(5);
		ds.setMaxIdle(20);
		ds.setMaxOpenPreparedStatements(180);
		return ds;
	}
	
//	public Connection openConnection() throws SQLException {
//		return dataSource.getConnection();
//	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException se) {

			}
		}
	}

	public static void closePreparedStatement(PreparedStatement pstmt) {
		if (pstmt != null) {

			try {
				pstmt.close();
			} catch (SQLException se) {

			}
		}
	}
	
	public static void closeResultSet(ResultSet res){
		if (res != null){
			try{
				res.close();
			}catch(SQLException se){
				
			}
		}
	}

}
