package com.example.jee.printing.platform.controller;

import com.example.jee.printing.platform.dao.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        UserDAO UserDAO = new UserDAO();
        UserDAO.deleteUser(userId);

        response.sendRedirect("AdminDashboardServlet");
        
           }
}
