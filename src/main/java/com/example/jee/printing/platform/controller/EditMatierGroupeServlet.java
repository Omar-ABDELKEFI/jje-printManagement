package com.example.jee.printing.platform.controller;

import com.example.jee.printing.platform.dao.MatierGroupeDAO;
import com.example.jee.printing.platform.model.MatierGroupe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditMatierGroupeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int matierGroupeId = Integer.parseInt(request.getParameter("matierGroupeId"));
        int matierId = Integer.parseInt(request.getParameter("matierId"));
        int groupeId = Integer.parseInt(request.getParameter("groupeId"));

        MatierGroupeDAO matierGroupeDAO = new MatierGroupeDAO();
        MatierGroupe matierGroupe = matierGroupeDAO.findById(matierGroupeId);

        if (matierGroupe != null) {
            matierGroupe.setMatierId(matierId);
            matierGroupe.setGroupeId(groupeId);
            matierGroupeDAO.updateMatierGroupe(matierGroupe);
        }

        response.sendRedirect("MatierGroupeManagementServlet");
    }
}
