package com.example.jee.printing.platform.controller;

import com.example.jee.printing.platform.dao.MatierDAO;
import com.example.jee.printing.platform.model.Matier;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditMatierServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve matier details from request parameters
        int matierId = Integer.parseInt(request.getParameter("matierId"));
        String name = request.getParameter("name");

        // Retrieve the existing matier from the database
        MatierDAO matierDAO = new MatierDAO();
        Matier matier = matierDAO.findById(matierId);

        // Update the matier details
        if (matier != null) {
            matier.setName(name);
            matierDAO.updateMatier(matier);
        }

        // Redirect back to the admin dashboard page after editing
        response.sendRedirect("MatierManagementServlet");
    }
}
