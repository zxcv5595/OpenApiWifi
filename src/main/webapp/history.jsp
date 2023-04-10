<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>와이파이 히스토리</title>
    <link rel="stylesheet" href="css/viewdetails.css">
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        h1 {
            margin-bottom: 20px;
        }
        .nav {
            display: flex;
            margin-bottom: 20px;
        }
        .nav a {
            text-decoration: none;
            color: #000;
        }
        .nav a:hover {
            color: #007BFF;
        }
        .nav span {
            margin: 0 5px;
        }
        .coordinate-inputs {
            margin-top: 20px;
            margin-bottom: 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
            text-align: center;
        }
        td {
            text-align: center;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:nth-child(odd) {
            background-color: #fff;
        }
    </style>
</head>
<body>
<h1>위치 히스토리 목록</h1>
<nav class="nav">
    <a href=/index.jsp>홈</a><span>|</span>
    <a href="/history.jsp">위치 히스토리 목록</a><span>|</span>
    <a href="/WifiSave">Open API와이파이 정보 가져오기</a><span>|</span>
    <a href=/bookmark-list.jsp>북마크 보기</a><span>|</span>
    <a href=/bookmarks.jsp>북마크 그룹 관리</a>
</nav>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>x좌표</th>
        <th>y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </tr>
    </thead>
    <tbody id="tableBody">
    <tr>
        <td colspan="5">히스토리가 없습니다.</td>
    </tr>
    </tbody>
</table>
<script type="text/javascript" src="js/history.js"></script>

</body>
</html>
