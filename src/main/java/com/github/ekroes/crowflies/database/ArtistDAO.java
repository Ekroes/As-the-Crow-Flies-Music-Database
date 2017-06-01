package com.github.ekroes.crowflies.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.github.ekroes.crowflies.model.Artist;

public class ArtistDAO {

	public List<Artist> listArtists() throws SQLException {

		List<Artist> artists = new ArrayList<Artist>();
		DataSource datasource = Driver.getDataSource();

		String sql = "SELECT * FROM artist " + "ORDER BY Artist_Id";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;

		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();

			while (res.next()) {
				artists.add(new Artist(res.getInt("Artist_ID"), res.getString("Artist_Name"),
						res.getString("Start_Year_Active"), res.getString("End_Year_Active")));
			}
			return artists;
		} finally {
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
			Driver.closeResultSet(res);

		}
	}

	public Artist getOneArtist(String artistId) throws SQLException {
		Artist anArtist = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		int id;

		String sql = "SELECT * FROM artist WHERE Artist_ID = ?";

		DataSource datasource = Driver.getDataSource();

		try {
			id = Integer.parseInt(artistId);
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			res = pstmt.executeQuery();

			if (res.next()) {
				anArtist = new Artist(res.getInt("Artist_Id"), res.getString("Artist_Name"),
						res.getString("Start_Year_Active"), res.getString("End_Year_Active"));

			}
			return anArtist;
		} finally {
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
			Driver.closeResultSet(res);
		}

	}

	public void insertArtist(Artist newArtistInfo) throws SQLException {
		String sql = "INSERT INTO artist (Artist_Name, Start_Year_Active, End_Year_Active) VALUES (?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		int id = 0;

		DataSource dataSource = Driver.getDataSource();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, newArtistInfo.getName());
			pstmt.setString(2, newArtistInfo.getStart());

			if (newArtistInfo.getEnd() == null || "".equals(newArtistInfo.getEnd())) {
				pstmt.setNull(3, java.sql.Types.INTEGER);
			} else {
				pstmt.setString(3, newArtistInfo.getEnd());

			}
			
			pstmt.executeUpdate();
			res = pstmt.getGeneratedKeys();

			if (res.next()) {
				id = res.getInt(1);
				newArtistInfo.setId(id);
			}
		} finally {
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
			Driver.closeResultSet(res);
		}

	}

	public void updateArtist(Artist theArtist) throws SQLException {
		String sql = "UPDATE artist SET Artist_Name = ?, Start_Year_Active = ?, End_Year_Active = ?"
				+ " WHERE Artist_Id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		DataSource dataSource = Driver.getDataSource();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, theArtist.getName());
			pstmt.setString(2, theArtist.getStart());
			if (theArtist.getEnd() == null || "".equals(theArtist.getEnd())) {
				pstmt.setNull(3, java.sql.Types.INTEGER);
			} else {
				pstmt.setString(3, theArtist.getEnd());

			}
			pstmt.setInt(4, theArtist.getId());
			pstmt.executeUpdate();
		} finally {
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);

		}

	}

	public void deleteArtist(String artistId) throws SQLException {
		String sql = "DELETE FROM artist WHERE Artist_ID = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		int id;

		DataSource dataSource = Driver.getDataSource();
		try {
			id = Integer.parseInt(artistId);
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} finally {
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
		}

	}

	public Artist getArtistName(String artistId) throws SQLException {
		Artist anArtist = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		int id;

		String sql = "SELECT Artist_Name FROM artist WHERE Artist_ID = ?";

		DataSource datasource = Driver.getDataSource();

		try {
			id = Integer.parseInt(artistId);
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			res = pstmt.executeQuery();

			if (res.next()) {
				anArtist = new Artist( res.getString("Artist_Name"));

			}
			return anArtist;
		} finally {
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
			Driver.closeResultSet(res);
		}
	}

	public List<Artist> searchForArtist(String artistName) throws SQLException {
		
		String sql = "SELECT * FROM artist WHERE Artist_Name LIKE ? ORDER BY Artist_Id";
		
		List<Artist> artists = new ArrayList<Artist>();
		DataSource datasource = Driver.getDataSource();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try{
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, artistName);
			res = pstmt.executeQuery();
			
			while(res.next()){
				artists.add(new Artist(res.getInt("Artist_Id"), res.getString("Artist_Name"), 
						res.getString("Start_Year_Active"), res.getString("End_Year_Active")));
			}
			return artists;
		} finally{
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
			Driver.closeResultSet(res);
		}

		

		
		
	}

}
