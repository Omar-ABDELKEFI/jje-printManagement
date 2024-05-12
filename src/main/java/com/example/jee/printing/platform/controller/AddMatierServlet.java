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
        String name = request.getParameter("name");

        Matier matier = new Matier();
        matier.setName(name);

        MatierDAO matierDAO = new MatierDAO();
        matierDAO.addMatier(matier);

        response.sendRedirect("MatierManagementServlet");
    }
}
