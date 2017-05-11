package com.github.ekroes.crowflies.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.github.ekroes.crowflies.model.Album;
import com.mysql.cj.fabric.xmlrpc.base.Data;

public class AlbumDAO {

	public List<Album> listAlbums(String artistId) throws SQLException {
		List<Album> album = new ArrayList<Album>();
		DataSource datasource = Driver.getDataSource();

		String sql = "SELECT * FROM album WHERE Artist_ID = ? " + "ORDER BY Year_Released";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		int id = 0;
		try {
			id = Integer.parseInt(artistId);
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			res = pstmt.executeQuery();

			while (res.next()) {
				album.add(new Album(res.getInt("Album_ID"), res.getString("Album_Name"), res.getString("Year_Released"),
						res.getInt("Artist_ID")));
			}
			return album;
		} finally {
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
			Driver.closeResultSet(res);
		}

	}

	public Album getOneAlbum(String albumId, String artistId) throws SQLException {
		Album anAlbum = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		int albumIds;
		int artistIds;

		String sql = "SELECT * FROM album WHERE Album_ID = ? AND Artist_ID = ?";

		DataSource dataSource = Driver.getDataSource();

		try {
			albumIds = Integer.parseInt(albumId);
			artistIds = Integer.parseInt(artistId);
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, albumIds);
			pstmt.setInt(2, artistIds);
			res = pstmt.executeQuery();

			if (res.next()) {
				anAlbum = new Album(res.getInt("Album_ID"), res.getString("Album_Name"), res.getString("Year_Released"),
						res.getInt("Artist_ID"));

			}
			return anAlbum;
		} finally {
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
			Driver.closeResultSet(res);

		}
	}

	public void insertAlbum(Album newAlbumInfo) throws SQLException {
		String sql = "INSERT INTO album (Album_Name, Year_Released, Artist_ID) VALUES (?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		int albumId = 0;

		DataSource dataSource = Driver.getDataSource();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, newAlbumInfo.getName());
			pstmt.setString(2, newAlbumInfo.getReleaseDate());
			pstmt.setInt(3, newAlbumInfo.getArtistId());
			pstmt.executeUpdate();
			res = pstmt.getGeneratedKeys();

			if (res.next()) {
				albumId = res.getInt(1);
				newAlbumInfo.setAlbumId(albumId);

			}
		} finally {
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
			Driver.closeResultSet(res);
		}

	}

	public void updateArtist(Album modifiedAlbum) throws SQLException {
		String sql = "UPDATE album SET Album_Name = ?, Year_Released = ? " + "WHERE Album_ID = ? AND Artist_ID = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		DataSource dataSource = Driver.getDataSource();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modifiedAlbum.getName());
			pstmt.setString(2, modifiedAlbum.getReleaseDate());
			pstmt.setInt(3, modifiedAlbum.getAlbumId());
			pstmt.setInt(4, modifiedAlbum.getArtistId());
			pstmt.executeUpdate();
		} finally {
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
		}

	}

	public void deleteAlbum(String albumId) throws SQLException {
		String sql = "DELETE FROM album WHERE Album_ID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int id;

		DataSource dataSource = Driver.getDataSource();
		try {
			id = Integer.parseInt(albumId);
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} finally {
			Driver.closeConnection(conn);
			Driver.closePreparedStatement(pstmt);
		}

	}

}
