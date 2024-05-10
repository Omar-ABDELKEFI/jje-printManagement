package com.example.jee.printing.platform.controller;

import java.io.*;
import java.util.Properties;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Properties prop = loadProperties();
        Connection connection = establishConnection(prop, res.getWriter());

        if (connection != null) {
            Statement stmt = null;
            String query = "SELECT s.subject_name " +
                           "FROM Users u " +
                           "JOIN TeacherSubject ts ON u.user_id = ts.user_id " +
                           "JOIN Subjects s ON ts.subject_id = s.subject_id " +
                           "WHERE u.user_id = 1;";

            List<String> subjectNames = new ArrayList<>();

            try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String subjectName = rs.getString("subject_name");
                    subjectNames.add(subjectName);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            req.setAttribute("subjectNames", subjectNames);
            req.getRequestDispatcher("/views/subjectList.jsp").forward(req, res);
        }
    }

    private Properties loadProperties() {
        Properties prop = new Properties();
        InputStream in = getClass().getResourceAsStream("/application.properties");
        if (in == null) {
            return null;
        } else {
            try {
                prop.load(in);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return prop;
        }
    }

    private Connection establishConnection(Properties prop, PrintWriter out) {
        String jdbc = prop.getProperty("mjjs.datasource.url");
        String username = prop.getProperty("mjjs.datasource.username");
        String password = prop.getProperty("mjjs.datasource.password");
        String className = prop.getProperty("mjjs.datasource.driverClassName");

        if (jdbc == null || username == null || password == null || className == null) {
            out.println("Example properties:");
            out.println("mjjs.datasource.url=jdbc:mysql://localhost:3306/mjjs");
            out.println("mjjs.datasource.username=ltiuser");
            out.println("mjjs.datasource.password=ltipassword");
            out.println("mjjs.datasource.driverClassName=com.mysql.jdbc.Driver");
            return null;
        }

        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            out.println("Missing JDBC Driver: " + className);
            System.out.println("Missing JDBC Driver: " + className);
            e.printStackTrace();
            return null;
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbc, username, password);
        } catch (SQLException e) {
            out.println("Your database server may be down. Or if it is up");
            out.println("your database is missing or inaccessible.");
            out.println("");
            out.println("CREATE DATABASE mjjs DEFAULT CHARACTER SET utf8;");
            out.println("GRANT ALL ON mjjs.* TO 'mjjsuser'@'localhost' IDENTIFIED BY 'mjjspassword';");
            out.println("GRANT ALL ON mjjs.* TO 'mjjsuser'@'127.0.0.1' IDENTIFIED BY 'mjjspassword';");
            e.printStackTrace();
            return null;
        }

        return connection;
    }
}