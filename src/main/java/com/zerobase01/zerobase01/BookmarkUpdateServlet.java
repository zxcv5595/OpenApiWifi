package com.zerobase01.zerobase01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/BookmarkUpdateServlet")
public class BookmarkUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            BookmarkDAO dao = new BookmarkDAO();
            // 해당 ID의 북마크 정보 조회
            BookmarkDTO bookmark = dao.getBookmarkById(id);
            String name = new String(bookmark.getName().getBytes("ISO-8859-1"), "UTF-8"); // 이름 필드에 대해 인코딩 적용
            bookmark.setName(name); // 인코딩된 이름으로 설정
            request.setAttribute("bookmark", bookmark);
            request.getRequestDispatcher("bookmarkUpdate.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
        int sequence = Integer.parseInt(request.getParameter("sequence"));

        try {
            BookmarkDAO dao = new BookmarkDAO();
            dao.updateBookmark(id, name, sequence, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        response.sendRedirect("bookmarks.jsp");
    }
}