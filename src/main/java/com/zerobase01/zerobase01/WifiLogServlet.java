package com.zerobase01.zerobase01;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

import static java.lang.Math.*;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "WifiLogServlet", urlPatterns = "/getWifiLog.do")
public class WifiLogServlet extends HttpServlet {

    private Gson gson = new Gson();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String latitudeParam = request.getParameter("latitude");
        String longitudeParam = request.getParameter("longitude");

        double latitude = Double.parseDouble(latitudeParam);
        double longitude = Double.parseDouble(longitudeParam);

        // WifiRecord 객체 생성
        WifiRecord record = new WifiRecord();
        record.setLatitude(latitude);
        record.setLongitude(longitude);
        try {
            record.setSearchDate(dateFormat.format(new Date()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // 데이터베이스에 WifiRecord 삽입
        insertWifiRecord(record);

        out.print(gson.toJson(record));
        out.flush();
    }

    private void insertWifiRecord(WifiRecord record) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\zxcv5\\IdeaProjects\\ZeroBase01\\src\\main\\db\\wifi_info.db");

            String createQuery = "CREATE TABLE IF NOT EXISTS wifi_logs (id INTEGER PRIMARY KEY AUTOINCREMENT, latitude REAL, longitude REAL, search_date TEXT)";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createQuery);

            String insertQuery = "INSERT INTO wifi_logs (latitude, longitude, search_date) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setDouble(1, record.getLatitude());
            pstmt.setDouble(2, record.getLongitude());
            pstmt.setString(3, record.getSearchDate().toString());
            pstmt.executeUpdate();

            pstmt.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
