package com.example.jee.printing.platform.controller;

import com.example.jee.printing.platform.dao.UserDAO;
import com.example.jee.printing.platform.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user details from request parameters
        int userId = Integer.parseInt(request.getParameter("userId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        // Retrieve other user details as needed

        // Update user details in the database
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(userId);
        if (user != null) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            // Update other user details as needed
            userDAO.updateUser(user);
        }

        // Redirect back to adminDashboard.jsp after editing
        response.sendRedirect("AdminDashboardServlet");
    }
}
