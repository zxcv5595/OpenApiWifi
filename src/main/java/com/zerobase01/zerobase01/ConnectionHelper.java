package com.zerobase01.zerobase01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    private static String DB_PATH = "C:\\Users\\zxcv5\\IdeaProjects\\ZeroBase01\\src\\main\\db\\wifi_info.db";
    private static String DB_URL = "jdbc:sqlite:" + DB_PATH;

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL);
        return connection;
    }
}
