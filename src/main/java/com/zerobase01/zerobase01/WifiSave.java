package com.zerobase01.zerobase01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


@WebServlet("/WifiSave")
public class WifiSave extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        response.sendRedirect("/WifiSaveResult");
    }

    public static WifiInfo fetchWifiInfo(int page, int recordsPerPage) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/" + URLEncoder.encode("66716e6d537a786336387052614142", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(Integer.toString((page - 1) * recordsPerPage + 1), "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(Integer.toString(page * recordsPerPage), "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("", "UTF-8")); // X_SWIFI_WRDOFC
        urlBuilder.append("/" + URLEncoder.encode("", "UTF-8")); // X_SWIFI_ADRES1

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() < 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();

        Gson gson = new Gson();
        WifiInfoResponse wifiInfoResponse = gson.fromJson(sb.toString(), WifiInfoResponse.class);

        return wifiInfoResponse.getWifiInfo();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String DB_PATH = "C:\\Users\\zxcv5\\IdeaProjects\\ZeroBase01\\src\\main\\db\\wifi_info.db";
        String DB_URL = "jdbc:sqlite:" + DB_PATH;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(DB_URL);
            connection.setAutoCommit(false);

            // Create table if not exists
            String createTableQuery = "CREATE TABLE IF NOT EXISTS wifi_records (" +
                    "control_number TEXT PRIMARY KEY, " +
                    "borough TEXT, " +
                    "wifi_name TEXT, " +
                    "road_name_address TEXT, " +
                    "detailed_address TEXT, " +
                    "installation_floor TEXT, " +
                    "installation_type TEXT, " +
                    "installer TEXT, " +
                    "service_classification TEXT, " +
                    "network_type TEXT, " +
                    "installation_year TEXT, " +
                    "indoor_outdoor_classification TEXT, " +
                    "wifi_connection_environment TEXT, " +
                    "latitude REAL, " +
                    "longitude REAL, " +
                    "work_date TEXT)";

            Statement statement = connection.createStatement();
            statement.execute(createTableQuery);

            // Prepare the insert query
            String insertQuery = "INSERT OR REPLACE INTO wifi_records (" +
                    "control_number, borough, wifi_name, road_name_address, detailed_address, installation_floor, " +
                    "installation_type, installer, service_classification, network_type, installation_year, " +
                    "indoor_outdoor_classification, wifi_connection_environment, latitude,longitude, work_date) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(insertQuery);


            WifiInfo wifiInfo = fetchWifiInfo(1, 1000);
            int totalCount = wifiInfo.getTotalCount();
            int totalPages = (int) Math.ceil((double) totalCount / 1000);

            for (int page = 1; page <= totalPages; page++) {
                if (page > 1) {
                    wifiInfo = fetchWifiInfo(page, 1000);
                }


                for (WifiRecord record : wifiInfo.getRecords()) {
                    preparedStatement.setString(1, record.getControlNumber());
                    preparedStatement.setString(2, record.getBorough());
                    preparedStatement.setString(3, record.getWifiName());
                    preparedStatement.setString(4, record.getRoadNameAddress());
                    preparedStatement.setString(5, record.getDetailedAddress());
                    preparedStatement.setString(6, record.getInstallationFloor());
                    preparedStatement.setString(7, record.getInstallationType());
                    preparedStatement.setString(8, record.getInstaller());
                    preparedStatement.setString(9, record.getServiceClassification());
                    preparedStatement.setString(10, record.getNetworkType());
                    preparedStatement.setString(11, record.getInstallationYear());
                    preparedStatement.setString(12, record.getIndoorOutdoorClassification());
                    preparedStatement.setString(13, record.getWifiConnectionEnvironment());
                    preparedStatement.setDouble(14, record.getLongitude());
                    preparedStatement.setDouble(15, record.getLatitude());
                    preparedStatement.setString(16, record.getWorkDate());

                    preparedStatement.executeUpdate();
                }
            }


            connection.commit();
            preparedStatement.close();
            connection.close();
            request.setAttribute("message", totalCount + "개의 WIFI정보를 정상적으로 저장하였습니다.");
            RequestDispatcher rd = request.getRequestDispatcher("/WifiSaveResult");
            rd.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
