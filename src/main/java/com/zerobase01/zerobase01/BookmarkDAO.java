package com.zerobase01.zerobase01;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;

public class BookmarkDAO {
    private final Connection connection;
    private PreparedStatement selectStatement;

    public BookmarkDAO() throws SQLException {
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

        String createTableQuery = "CREATE TABLE IF NOT EXISTS bookmarks ("
                + "id INTEGER PRIMARY KEY,"
                + "name VARCHAR(255) NOT NULL UNIQUE,"
                + "sequence INTEGER NOT NULL UNIQUE CHECK(sequence >= 0),"
                + "created_at TIMESTAMP DEFAULT (datetime('now', 'localtime')),"
                + "updated_at TIMESTAMP DEFAULT NULL"
                + ")";


        Statement statement = connection.createStatement();
        statement.execute(createTableQuery);
    }


    //삽입
    public void addBookmark(String name, String sequence, ServletResponse response) throws SQLException, IOException {
        String insertQuery = "INSERT INTO bookmarks (name, sequence) VALUES (?, ?)";
        PreparedStatement pstm = null;
        try {
            pstm = connection.prepareStatement(insertQuery);
            pstm.setString(1, name); // UTF-8로 인코딩된 바이트 배열을 설정
            int seq = Integer.parseInt(sequence);
            if (seq < 0) {
                throw new IllegalArgumentException();
            }
            pstm.setInt(2, seq);
            pstm.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter printw = response.getWriter();

                printw.println("<script language='javascript'>");
                printw.println("alert('북마크 이름 혹은 순서가 중복 됐습니다.')");
                printw.println("</script>");

                printw.flush();
            } else {
                throw e;
            }
        } catch (IllegalArgumentException e) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter printw = response.getWriter();

            printw.println("<script language='javascript'>");
            printw.println("alert('순서는 0 이상의 자연수여야 합니다.')");
            printw.println("</script>");

            printw.flush();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
        }
    }



    //조회
    public ResultSet getBookmarks() throws SQLException {
        String query = "SELECT id, name, sequence, strftime('%Y-%m-%dT%H:%M:%S', created_at) AS formatted_created_at, IFNULL(strftime('%Y-%m-%dT%H:%M:%S', updated_at), '') AS formatted_updated_at FROM bookmarks ORDER BY sequence ASC";
        selectStatement = connection.prepareStatement(query);
        ResultSet resultSet = selectStatement.executeQuery();
        return resultSet;
    }

    // 삭제
    public void deleteBookmark(int id) throws SQLException {
        String deleteQuery = "DELETE FROM bookmarks WHERE id = ?";
        PreparedStatement pstm = null;
        try {
            pstm = connection.prepareStatement(deleteQuery);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
        }
    }

    // 수정
    public void updateBookmark(int id, String name, int sequence, ServletResponse response) throws SQLException, IllegalArgumentException, IOException {
        if (sequence < 0) {
            throw new IllegalArgumentException();
        }
        String oldName = getBookmarkById(id).getName();
        String updateQuery = "UPDATE bookmarks SET name = ?, sequence = ?, updated_at = datetime('now', 'localtime') WHERE id = ?";
        PreparedStatement pstm = null;
        try {
            pstm = connection.prepareStatement(updateQuery);
            pstm.setString(1, name);
            pstm.setInt(2, sequence);
            pstm.setInt(3, id);
            pstm.executeUpdate();

            // update bookmarks_elements table

            if (!oldName.equals(name)) {
                String updateQueryBookmarkElements = "UPDATE bookmarks_elements SET bookmark_name = ? WHERE bookmark_name = ?";
                PreparedStatement pstmBookmarkElements = null;

                try {
                    pstmBookmarkElements = connection.prepareStatement(updateQueryBookmarkElements);
                    pstmBookmarkElements.setString(1, name);
                    pstmBookmarkElements.setString(2, oldName);
                    pstmBookmarkElements.executeUpdate();
                } catch (SQLException e) {
                    throw e;
                } finally {
                    if (pstmBookmarkElements != null) {
                        pstmBookmarkElements.close();
                    }
                }
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter printw = response.getWriter();

                printw.println("<script language='javascript'>");
                printw.println("alert('북마크 이름 혹은 순서가 중복 됐습니다.')");
                printw.println("</script>");

                printw.flush();
            } else {
                throw e;
            }
        } catch (IllegalArgumentException e) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter printw = response.getWriter();

            printw.println("<script language='javascript'>");
            printw.println("alert('순서는 0 이상의 자연수여야 합니다.')");
            printw.println("</script>");

            printw.flush();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
        }
    }





    public BookmarkDTO getBookmarkById(int id) throws SQLException {
        String query = "SELECT * FROM bookmarks WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        try {
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int bookmarkId = rs.getInt("id");
                String name = rs.getString("name");
                int sequence = rs.getInt("sequence");
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp updatedAt = rs.getTimestamp("updated_at");
                return new BookmarkDTO(bookmarkId, name, sequence, createdAt, updatedAt);
            } else {
                return null;
            }
        } finally {
            if (pstm != null) {
                pstm.close();
            }
        }
    }

    public ArrayList<String> getBookmarkNames() throws SQLException {
        ArrayList<String> bookmarkNames = new ArrayList<>();
        String query = "SELECT name FROM bookmarks ORDER BY sequence ASC";
        PreparedStatement pstm = connection.prepareStatement(query);
        ResultSet rs = null;
        try {
            rs = pstm.executeQuery();
            while (rs.next()) {
                bookmarkNames.add(new String(rs.getBytes("name"), "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
        }
        return bookmarkNames;
    }




}