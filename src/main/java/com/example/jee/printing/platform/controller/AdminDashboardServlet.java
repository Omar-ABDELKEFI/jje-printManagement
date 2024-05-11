package com.example.jee.printing.platform.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.jee.printing.platform.dao.UserDAO;
import com.example.jee.printing.platform.model.User;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminDashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the list of users
        UserDAO userDAO = new UserDAO();
        List<User> userList = userDAO.findAll();

        // Set the list of users as a request attribute
        request.setAttribute("userList", userList);

        // Forward the request to adminDashboard.jsp
        request.getRequestDispatcher("/views/adminDashboard.jsp").forward(request, response);
    }
}
