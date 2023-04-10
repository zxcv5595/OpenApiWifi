<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.zerobase01.zerobase01.BookmarkDAO" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>북마크 그룹</title>
  <link rel="stylesheet" type="text/css" href = "css/bookmarks.css">
</head>
<body>
<h1>북마크 그룹</h1>
<nav class="nav">
  <a href=/index.jsp>홈</a><span>|</span>
  <a href=/history.jsp>위치 히스토리 목록</a><span>|</span>
  <a href="/WifiSave">Open API와이파이 정보 가져오기</a><span>|</span>
  <a href=/bookmark-list.jsp>북마크 보기</a><span>|</span>
  <a href=/bookmarks.jsp>북마크 그룹 관리</a>
</nav>
<div class="coordinate-inputs">
  <button type="button" id="add-bookmark" onclick="location.href='bookmarksAdd.jsp'">북마크 그룹 생성하기</button>
</div>
<table>
  <thead>
  <tr>
    <th>id</th>
    <th>북마크 이름</th>
    <th>순서</th>
    <th>등록일자</th>
    <th>수정일자</th>
    <th>비고</th>
  </tr>
  </thead>
  <tbody>

  <% BookmarkDAO dao = new BookmarkDAO();
    ResultSet rs = dao.getBookmarks();
    if (!rs.next()) {
  %>

  <tr>
    <td colspan="6">북마크를 추가해보세요</td>
  </tr>

  <% } else {
    do {
  %>

  <tr>
    <td><%= rs.getInt("id") %></td>
    <td><%= rs.getString("name") %></td>
    <td><%= rs.getInt("sequence") %></td>
    <td><%= rs.getString("formatted_created_at") %></td>
    <td><%= rs.getString("formatted_updated_at") %></td>
    <td>
      <a href="bookmarksUpdate.jsp?id=<%= rs.getInt("id") %>">수정</a>
      <a href="/BookmarkDeleteServlet?id=<%= rs.getInt("id") %>">삭제</a>
    </td>
  </tr>

  <% } while (rs.next());
  } %>

  </tbody>
</table>
</body>
</html>
