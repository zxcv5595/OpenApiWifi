<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>와이파이 정보 구하기</title>
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
<h1>와이파이 정보 구하기</h1>
<nav class="nav">
    <a href=/index.jsp>홈</a><span>|</span>
    <a href=/history.jsp>위치 히스토리 목록</a><span>|</span>
    <a href="/WifiSave">Open API와이파이 정보 가져오기</a><span>|</span>
    <a href=/bookmark-list.jsp>북마크 보기</a><span>|</span>
    <a href=/bookmarks.jsp>북마크 그룹 관리</a>
</nav>
    <div class="coordinate-inputs">
        LAT: <input type="text" name="latitude" id="latitude" value="0.0">
        LNT: <input type="text" name="longitude" id="longitude" value="0.0">
        <button type="button" id="get-location">내 위치 가져오기</button>
        <button type="button" id="find-wifi">근처 WIFI정보 보기</button>
    </div>
<table>
    <thead>
    <tr>
        <th>거리(km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
    </tr>
    </tbody>
</table>
<script type="text/javascript" src="js/index.js"></script>

</body>
</html>