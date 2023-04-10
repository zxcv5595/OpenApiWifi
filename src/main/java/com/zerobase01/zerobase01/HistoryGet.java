package com.zerobase01.zerobase01;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(name = "HistoryGet", urlPatterns = "/HistoryGet.do")
public class HistoryGet extends HttpServlet {
    private Connection conn;

    public void init() throws ServletException {
        try {
            ServletContext context = getServletContext();
            String DB_PATH = "C:\\Users\\zxcv5\\IdeaProjects\\ZeroBase01\\src\\main\\db\\wifi_info.db";
            String DB_URL = "jdbc:sqlite:" + DB_PATH;
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL);
            context.setAttribute("dbConnection", conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletContext context = getServletContext();
            conn = (Connection) context.getAttribute("dbConnection");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM wifi_logs ORDER BY id DESC");

            List<Object> resultList = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("id", rs.getInt("id"));
                resultMap.put("x", rs.getDouble("latitude"));
                resultMap.put("y", rs.getDouble("longitude"));
                resultMap.put("date", rs.getString("search_date"));
                resultList.add(resultMap);
            }

            rs.close();
            stmt.close();

            Gson gson = new Gson();
            String json = gson.toJson(resultList);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void destroy() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
