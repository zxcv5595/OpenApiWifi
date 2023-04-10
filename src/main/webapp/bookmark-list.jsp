<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.zerobase01.zerobase01.BookmarksElementDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.zerobase01.zerobase01.BookmarksElementDTO" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>북마크 그룹</title>
    <link rel="stylesheet" type="text/css" href = "css/bookmarks.css">
</head>
<body>
<h1>북마크 보기</h1>
<nav class="nav">
    <a href=/index.jsp>홈</a><span>|</span>
    <a href=/history.jsp>위치 히스토리 목록</a><span>|</span>
    <a href="/WifiSave">Open API와이파이 정보 가져오기</a><span>|</span>
    <a href=/bookmark-list.jsp>북마크 보기</a><span>|</span>
    <a href=/bookmarks.jsp>북마크 그룹 관리</a>
</nav>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>와이파이명</th>
        <th>등록일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <% try {
        BookmarksElementDAO dao = new BookmarksElementDAO();
        ArrayList<BookmarksElementDTO> bookmarkElements = dao.getBookmarkElements();
        for (BookmarksElementDTO bookmarkElement : bookmarkElements) {
    %>
    <tr>
        <td><%= bookmarkElement.getId() %></td>
        <td><%= bookmarkElement.getBookmarkName() %></td>
        <td><%= bookmarkElement.getWifiName() %></td>
        <td><%= bookmarkElement.getCreatedAt() %></td>
        <td><a href="bookmarksDelete.jsp?id=<%= bookmarkElement.getId() %>">삭제</a></td>
    </tr>
    <%
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    %>
    </tbody>
</table>

</body>
</html>
