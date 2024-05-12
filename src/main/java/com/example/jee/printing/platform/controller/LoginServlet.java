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


public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findByEmailAndPassword(email, password);

        if (user != null && user.getStatus().equals("active")) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            switch (user.getRole()) {
                case "admin":
                    List<User> userList = userDAO.findAll();
                    request.setAttribute("userList", userList);

                    response.sendRedirect("AdminDashboardServlet");

                    break;
                case "teacher":
                    response.sendRedirect("/printRequest");
                    break;
                case "agent":
                    response.sendRedirect("/agentDashboard");
                    break;
                default:
                    response.sendRedirect("/views/login.jsp"); // Redirect to a default page if role not recognized
            }
        } else {
            response.sendRedirect("/views/login.jsp");
        }
    }
}
