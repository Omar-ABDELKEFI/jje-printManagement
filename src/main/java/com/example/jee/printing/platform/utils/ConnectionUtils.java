package com.example.jee.printing.platform.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtils {
    private static String url;
    private static String username;
    private static String password;

    static {
        Properties prop = new Properties();
        try {
            prop.load(ConnectionUtils.class.getResourceAsStream("/application.properties"));
            url = prop.getProperty("mjjs.datasource.url");
            username = prop.getProperty("mjjs.datasource.username");
            password = prop.getProperty("mjjs.datasource.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}

