package com.zerobase01.zerobase01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@WebServlet("/addBookmark")
public class AddBookmarkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookmarkName = new String(request.getParameter("bookmarkName").getBytes("ISO-8859-1"), "UTF-8");
        String wifiName = new String(request.getParameter("wifiName").getBytes("ISO-8859-1"), "UTF-8");
        String createdAt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));



        BookmarksElementDAO dao = new BookmarksElementDAO();
        boolean success = false;
        try {
            success = dao.addBookmarkElement(bookmarkName, wifiName, createdAt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (success) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>window.alert('성공적으로 추가되었습니다.'); history.go(-1);</script>");
        } else {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>window.alert('실패 하였습니다.'); history.go(-1);</script>");
        }


    }
}
