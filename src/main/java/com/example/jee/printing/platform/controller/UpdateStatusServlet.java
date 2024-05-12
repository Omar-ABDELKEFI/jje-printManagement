package com.example.jee.printing.platform.controller;

import com.example.jee.printing.platform.dao.UserDAO;
import com.example.jee.printing.platform.model.User;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UpdateStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String status = request.getParameter("status");
        
        updateUserStatus(userId, status);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setStatus(HttpServletResponse.SC_OK);

        
        response.sendRedirect("AdminDashboardServlet");
    }
    
    private void updateUserStatus(int userId, String status) {
       UserDAO userDAO = new UserDAO();
       userDAO.updateStatus(userId,status);

    }
}
