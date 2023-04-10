<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.zerobase01.zerobase01.BookmarkDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.zerobase01.zerobase01.BookmarksElementDAO" %>
<%@ page import="com.zerobase01.zerobase01.BookmarksElementDTO" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>북마크 그룹 추가</title>
    <style>
        .detail-container {
            margin: 10px auto;
            width: 80%;
            margin-left: 0;
        }

        table {
            width: 80%;
            border-collapse: collapse;
            margin-left: 0;
        }

        th {
            background-color: #4CAF50;
            color: #fff;
            padding: 10px;
            text-align: center;
            border: 1px solid #ccc;
        }

        td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: left;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        input[type="submit"] {
            margin-top: 10px;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-align: center;
            display: block;
            margin: 0 auto;
        }

    </style>

</head>
<body>
<h1>북마크 삭제</h1>
<nav class="nav">
    <a href=/index.jsp>홈</a><span>|</span>
    <a href=/history.jsp>위치 히스토리 목록</a><span>|</span>
    <a href="/WifiSave">Open API와이파이 정보 가져오기</a><span>|</span>
    <a href=/bookmark-list.jsp>북마크 보기</a><span>|</span>
    <a href=/bookmarks.jsp>북마크 그룹 관리</a>
</nav>

<div class="detail-container">
    <h3>정말 삭제 하시겠습니까?</h3>
    <form method="post" action="bookmarksDeleteProcess.jsp">
        <table>
            <tbody>
                <%
try {
    int id = Integer.parseInt(request.getParameter("id"));
    BookmarksElementDAO dao = new BookmarksElementDAO();
    BookmarksElementDTO bookmarkElement = dao.getBookmarkElementById(id);
%>
            <form method="post" action="bookmarksDeleteProcess.jsp">
                <input type="hidden" name="id" value="<%= bookmarkElement.getId() %>">
                <table>
                    <tbody>
                    <tr>
                        <th>북마크 이름</th>
                        <td><%= bookmarkElement.getBookmarkName() %></td>
                    </tr>
                    <tr>
                        <th>와이파이명</th>
                        <td><%= bookmarkElement.getWifiName() %></td>
                    </tr>
                    <tr>
                        <th>등록일자</th>
                        <td><%= bookmarkElement.getCreatedAt() %></td>
                    </tr>
                    </tbody>
                </table>
                <input type="submit" value="삭제하기">
            </form>
                <%
} catch (SQLException e) {
    e.printStackTrace();
}
%>

</div>
</body>
</html>
