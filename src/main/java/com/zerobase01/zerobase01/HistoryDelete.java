package com.zerobase01.zerobase01;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "delete", urlPatterns = "/delete.do")
public class HistoryDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            System.err.println("ID가 전달되지 않았습니다.");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID가 전달되지 않았습니다.");
            return;
        }
        int idInt = Integer.parseInt(id);
        String DB_PATH = "C:\\Users\\zxcv5\\IdeaProjects\\ZeroBase01\\src\\main\\db\\wifi_info.db";
        String DB_URL = "jdbc:sqlite:" + DB_PATH;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM wifi_logs WHERE id = ?")) {
            pstmt.setInt(1, idInt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터 삭제 중 오류가 발생했습니다.");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/history.jsp"); // 삭제 후 목록 페이지로 이동
    }
}