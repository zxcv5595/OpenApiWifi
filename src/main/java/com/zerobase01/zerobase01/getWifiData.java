package com.zerobase01.zerobase01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "getWifiData", urlPatterns = "/getWifiData.do")
public class getWifiData extends HttpServlet {

    private Gson gson = new Gson();

    private static double haversineDistance(double x1, double y1, double x2, double y2) {
        double distance;
        double radius = 6371;
        double toRadian = Math.PI / 180;

        double deltaLatitude = Math.abs(x1 - x2) * toRadian;
        double deltaLongitude = Math.abs(y1 - y2) * toRadian;

        double sinDeltaLat = Math.sin(deltaLatitude / 2);
        double sinDeltaLng = Math.sin(deltaLongitude / 2);
        double squareRoot = Math.sqrt(sinDeltaLat * sinDeltaLat + Math.cos(x1 * toRadian) * Math.cos(x2 * toRadian) * sinDeltaLng * sinDeltaLng);

        distance = 2 * radius * Math.asin(squareRoot);

        return distance;
    }

    public static List<WifiRecord> getWifiRecords(double latitude, double longitude) throws ClassNotFoundException {
        List<WifiRecord> records = new ArrayList<>();
        String DB_PATH = "C:\\Users\\zxcv5\\IdeaProjects\\ZeroBase01\\src\\main\\db\\wifi_info.db";
        String DB_URL = "jdbc:sqlite:" + DB_PATH;
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement pstmt = conn.prepareStatement("SELECT *, 2 * 6371 * ASIN(SQRT(POWER(SIN(RADIANS((latitude - ?) / 2)), 2) + COS(RADIANS(?)) * COS(RADIANS(latitude)) * POWER(SIN(RADIANS((longitude - ?) / 2)), 2))) AS distance FROM wifi_records ORDER BY distance ASC LIMIT 20")) {
            pstmt.setDouble(1, latitude);
            pstmt.setDouble(2, latitude);
            pstmt.setDouble(3, longitude);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    WifiRecord record = new WifiRecord();
                    record.setControlNumber(rs.getString("control_number"));
                    record.setBorough(rs.getString("borough"));
                    record.setWifiName(rs.getString("wifi_name"));
                    record.setRoadNameAddress(rs.getString("road_name_address"));
                    record.setDetailedAddress(rs.getString("detailed_address"));
                    record.setInstallationFloor(rs.getString("installation_floor"));
                    record.setInstallationType(rs.getString("installation_type"));
                    record.setInstaller(rs.getString("installer"));
                    record.setServiceClassification(rs.getString("service_classification"));
                    record.setNetworkType(rs.getString("network_type"));
                    record.setInstallationYear(rs.getString("installation_year"));
                    record.setIndoorOutdoorClassification(rs.getString("indoor_outdoor_classification"));
                    record.setWifiConnectionEnvironment(rs.getString("wifi_connection_environment"));
                    record.setLatitude(rs.getDouble("latitude"));
                    record.setLongitude(rs.getDouble("longitude"));
                    record.setWorkDate(rs.getString("work_date"));

                    double distance = haversineDistance(latitude, longitude, record.getLatitude(), record.getLongitude());
                    record.setDistance(distance);

                    records.add(record);
                }

            }
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }

        return records;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String latitudeParam = request.getParameter("latitude");
        String longitudeParam = request.getParameter("longitude");


        double latitude = Double.parseDouble(latitudeParam);
        double longitude = Double.parseDouble(longitudeParam);

        List<WifiRecord> records = null;
        try {
            records = getWifiRecords(latitude, longitude);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String jsonString = gson.toJson(records);
        out.print(jsonString);
        out.flush();
    }
}