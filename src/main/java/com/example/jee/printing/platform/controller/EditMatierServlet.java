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
        int matierId = Integer.parseInt(request.getParameter("matierId"));
        String name = request.getParameter("name");

        MatierDAO matierDAO = new MatierDAO();
        Matier matier = matierDAO.findById(matierId);

        if (matier != null) {
            matier.setName(name);
            matierDAO.updateMatier(matier);
        }

        response.sendRedirect("MatierManagementServlet");
    }
}
