package com.zerobase01.zerobase01;

import java.sql.*;
import java.util.ArrayList;

public class BookmarksElementDAO {
    private Connection connection;

    public BookmarksElementDAO() {
        try {
            // 드라이버 로드
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            // 데이터베이스 연결 설정
            String DB_PATH = "C:\\Users\\zxcv5\\IdeaProjects\\ZeroBase01\\src\\main\\db\\wifi_info.db";
            String DB_URL = "jdbc:sqlite:" + DB_PATH;
            connection = DriverManager.getConnection(DB_URL);

            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS bookmarks_elements ("
                + "id INTEGER PRIMARY KEY,"
                + "bookmark_name VARCHAR(255) NOT NULL,"
                + "wifi_name VARCHAR(255) NOT NULL,"
                + "created_at TIMESTAMP DEFAULT (datetime('now', 'localtime'))"
                + ")";
        Statement stmt = connection.createStatement();
        stmt.execute(createTableQuery);
        stmt.close();
    }

    public boolean addBookmarkElement(String bookmarkName, String wifiName, String createdAt) throws SQLException {
        String query = "INSERT INTO bookmarks_elements (bookmark_name, wifi_name, created_at) VALUES (?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setString(1, bookmarkName);
        pstm.setString(2, wifiName);
        pstm.setString(3, createdAt);
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    public ArrayList<BookmarksElementDTO> getBookmarkElements() throws SQLException {
        ArrayList<BookmarksElementDTO> bookmarkElements = new ArrayList<>();
        String deleteQuery = "DELETE FROM bookmarks_elements WHERE bookmark_name NOT IN (SELECT name FROM bookmarks)";
        String selectQuery = "SELECT *, datetime(created_at, 'localtime') as created_at_str FROM bookmarks_elements ORDER BY bookmark_name ASC";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(deleteQuery);
        ResultSet rs = stmt.executeQuery(selectQuery);
        while (rs.next()) {
            int id = rs.getInt("id");
            String bookmarkName = rs.getString("bookmark_name");
            String wifiName = rs.getString("wifi_name");
            String createdAtStr = rs.getString("created_at_str");
            Timestamp createdAt = Timestamp.valueOf(createdAtStr);
            bookmarkElements.add(new BookmarksElementDTO(id, bookmarkName, wifiName, createdAt));
        }
        rs.close();
        stmt.close();
        return bookmarkElements;
    }


    public void updateBookmarkElement(String oldBookmarkName, String newBookmarkName) throws SQLException {
        String updateQuery = "UPDATE bookmarks_elements SET bookmark_name = ? WHERE bookmark_name = ?";
        PreparedStatement pstm = connection.prepareStatement(updateQuery);
        pstm.setString(1, newBookmarkName);
        pstm.setString(2, oldBookmarkName);
        pstm.executeUpdate();
        pstm.close();
    }

    public BookmarksElementDTO getBookmarkElementById(int id) throws SQLException {
        String query = "SELECT *, datetime(created_at, 'localtime') as created_at_str  FROM bookmarks_elements WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            String bookmarkName = rs.getString("bookmark_name");
            String wifiName = rs.getString("wifi_name");
            String createdAtStr = rs.getString("created_at_str");
            Timestamp createdAt = Timestamp.valueOf(createdAtStr);
            pstm.close();
            return new BookmarksElementDTO(id, bookmarkName, wifiName, createdAt);
        } else {
            pstm.close();
            return null;
        }
    }


    public boolean deleteBookmarkElement(int id) throws SQLException {
        String query = "DELETE FROM bookmarks_elements WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setInt(1, id);
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }
}
