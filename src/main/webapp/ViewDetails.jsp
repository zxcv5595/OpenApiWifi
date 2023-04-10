<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.zerobase01.zerobase01.BookmarkDAO" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>와이파이 상세정보</title>
    <link rel="stylesheet" type="text/css" href = "css/viewdetails.css">

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
    <form action="addBookmark" method="POST">
        <% String wifiName = request.getParameter("wifiName"); %>
        <input type="hidden" name="wifiName" value="<%= wifiName %>">
        <select name="bookmarkName">
            <option value="" selected disabled>북마크 그룹 이름 선택</option>
            <% BookmarkDAO dao = new BookmarkDAO();
                ArrayList<String> bookmarkNames = dao.getBookmarkNames();
                for (String bookmarkName : bookmarkNames) { %>
            <option value="<%= bookmarkName %>"><%= bookmarkName %></option>
            <% } %>
        </select>
        <button type="submit" id="add-bookmark">북마크 추가하기</button>
    </form>
</div>


<div class="detail-container">
    <div><span>거리(km)</span><div id="distance"></div></div>
    <div><span>관리번호</span><div id="control-number"></div></div>
    <div><span>자치구</span><div id="borough"></div></div>
    <div><span>와이파이명</span><div id="wifi-name"></div></div>
    <div><span>도로명주소</span><div id="road-name-address"></div></div>
    <div><span>상세주소</span><div id="detailed-address"></div></div>
    <div><span>설치위치(층)</span><div id="installation-floor"></div></div>
    <div><span>설치유형</span><div id="installation-type"></div></div>
    <div><span>설치기관</span><div id="installer"></div></div>
    <div><span>서비스구분</span><div id="service-classification"></div></div>
    <div><span>망종류</span><div id="network-type"></div></div>
    <div><span>설치년도</span><div id="installation-year"></div></div>
    <div><span>실내외구분</span><div id="indoor-outdoor-classification"></div></div>
    <div><span>WIFI접속환경</span><div id="wifi-connection-environment"></div></div>
    <div><span>X좌표</span><div id="lat"></div></div>
    <div><span>Y좌표</span><div id="lnt"></div></div>
    <div><span>작업일자</span><div id="work-date"></div></div>
</div>

<script>
    // URL에서 쿼리스트링을 가져오기 위한 함수
    function getParameterByName(name, url = window.location.href) {
        name = name.replace(/[\[\]]/g, '\\$&');
        const regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }

    // 가져온 쿼리스트링을 각 필드에 설정
    document.getElementById('distance').innerHTML = getParameterByName('distance');
    document.getElementById('control-number').innerHTML = getParameterByName('controlNumber');
    document.getElementById('borough').innerHTML = getParameterByName('borough');
    document.getElementById('borough').innerHTML = getParameterByName('borough');
    document.getElementById('wifi-name').innerHTML = getParameterByName('wifiName');
    document.getElementById('road-name-address').innerHTML = getParameterByName('roadNameAddress');
    document.getElementById('detailed-address').innerHTML = getParameterByName('detailedAddress');
    document.getElementById('installation-floor').innerHTML = getParameterByName('installationFloor');
    document.getElementById('installation-type').innerHTML = getParameterByName('installationType');
    document.getElementById('installer').innerHTML = getParameterByName('installer');
    document.getElementById('service-classification').innerHTML = getParameterByName('serviceClassification');
    document.getElementById('network-type').innerHTML = getParameterByName('networkType');
    document.getElementById('installation-year').innerHTML = getParameterByName('installationYear');
    document.getElementById('indoor-outdoor-classification').innerHTML = getParameterByName('indoorOutdoorClassification');
    document.getElementById('wifi-connection-environment').innerHTML = getParameterByName('wifiConnectionEnvironment');
    document.getElementById('lat').innerHTML = getParameterByName('latitude');
    document.getElementById('lnt').innerHTML = getParameterByName('longitude');
    document.getElementById('work-date').innerHTML = getParameterByName('workDate');
</script>
<script type="text/javascript" src="js/index.js"></script>
</body>
</html>
