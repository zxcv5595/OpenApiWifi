package com.zerobase01.zerobase01;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookmarkDeleteServlet")
public class BookmarkDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            BookmarkDAO dao = new BookmarkDAO();
            dao.deleteBookmark(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("bookmarks.jsp");
    }
}
