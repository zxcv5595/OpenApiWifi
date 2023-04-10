<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.zerobase01.zerobase01.BookmarkDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.zerobase01.zerobase01.BookmarkDTO" %>

<%
    int flag = -1;
    if (request.getMethod().equalsIgnoreCase("post")) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
        String sequenceS = request.getParameter("sequence");

        if (!name.isEmpty() && !sequenceS.isEmpty()) {
            int sequence = Integer.parseInt(sequenceS);
            if (sequence >= 0) {
                BookmarkDAO dao = new BookmarkDAO();
                dao.updateBookmark(id, name, sequence, response);
                flag = 1;
            } else {
                flag = 0;
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter printw = response.getWriter();

                printw.println("<script language='javascript'>");
                printw.println("alert('순서는 0 이상의 자연수여야 합니다.')");
                printw.println("</script>");

                printw.flush();
            }
        } else {
            flag = 0;
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter printw = response.getWriter();

            printw.println("<script language='javascript'>");
            printw.println("alert('값을 입력해주세요')");
            printw.println("</script>");

            printw.flush();
        }
    } else {
        int id = Integer.parseInt(request.getParameter("id"));
        BookmarkDAO dao = new BookmarkDAO();
        BookmarkDTO bookmark = dao.getBookmarkById(id);

        request.setAttribute("bookmark", bookmark);
    }
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>북마크 그룹 수정</title>
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
<h1>북마크 그룹 수정</h1>
<nav class="nav">
    <a href=/index.jsp>홈</a><span>|</span>
    <a href=/history.jsp>위치 히스토리 목록</a><span>|</span>
    <a href="/WifiSave">Open API와이파이 정보 가져오기</a><span>|</span>
    <a href=/bookmark-list.jsp>북마크 보기</a><span>|</span>
    <a href=/bookmarks.jsp>북마크 그룹 관리</a>
</nav>

<div class="detail-container">
    <form method="post" action="bookmarksUpdate.jsp">
        <table>
            <tr>
                <th>북마크 이름</th>
                <td><input type="text" id="name" name="name"></td>
            </tr>
            <tr>
                <th>순서</th>
                <td><input type="text" id="sequence" name="sequence"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="hidden" name="id" value="<%= request.getParameter("id") %>"><input
                        type="submit" value="수정"></td>
            </tr>
        </table>
    </form>
    <% if (flag == 0) { %>
    <p>북마크 이름과 순서를 입력해주세요.</p>
    <% } else if(flag == 1) { %>
    <p>북마크 그룹이 수정되었습니다.</p>
    <% } %>

</div>
</body>
</html>
