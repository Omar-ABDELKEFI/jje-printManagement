package com.example.jee.printing.platform.controller;

import com.example.jee.printing.platform.dao.MatierGroupeDAO;
import com.example.jee.printing.platform.model.MatierGroupe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddMatierGroupeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve matierGroupe details from request parameters
        int matierId = Integer.parseInt(request.getParameter("matierId"));
        int groupeId = Integer.parseInt(request.getParameter("groupeId"));

        // Create a new matierGroupe object
        MatierGroupe matierGroupe = new MatierGroupe();
        matierGroupe.setMatierId(matierId);
        matierGroupe.setGroupeId(groupeId);

        // Add the matierGroupe to the database
        MatierGroupeDAO matierGroupeDAO = new MatierGroupeDAO();
        matierGroupeDAO.addMatierGroupe(matierGroupe);

        // Redirect back to the page after adding the matierGroupe
        response.sendRedirect("MatierGroupeManagementServlet");
    }
}
