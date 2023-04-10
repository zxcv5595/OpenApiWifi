<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.zerobase01.zerobase01.BookmarksElementDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.zerobase01.zerobase01.BookmarksElementDTO" %>

<%
    int bookmarkElementId = Integer.parseInt(request.getParameter("id"));
    boolean result = false;
    try {
        BookmarksElementDAO dao = new BookmarksElementDAO();
        result = dao.deleteBookmarkElement(bookmarkElementId);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    if (result) {
        response.sendRedirect("/bookmark-list.jsp");
    } else {
%>
<script>
    alert('북마크 삭제에 실패했습니다.');
    window.location.href='/bookmark-list.jsp';
</script>
<%
    }
%>