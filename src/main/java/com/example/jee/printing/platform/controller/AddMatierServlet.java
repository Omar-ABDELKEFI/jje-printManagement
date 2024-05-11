package com.example.jee.printing.platform.controller;

import com.example.jee.printing.platform.dao.MatierDAO;
import com.example.jee.printing.platform.model.Matier;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddMatierServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve matier details from request parameters
        String name = request.getParameter("name");

        // Create a new matier object
        Matier matier = new Matier();
        matier.setName(name);

        // Add the matier to the database
        MatierDAO matierDAO = new MatierDAO();
        matierDAO.addMatier(matier);

        // Redirect back to the page after adding the matier
        response.sendRedirect("MatierManagementServlet");
    }
}
