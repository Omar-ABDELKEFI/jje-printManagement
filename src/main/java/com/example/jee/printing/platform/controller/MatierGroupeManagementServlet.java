package com.example.jee.printing.platform.controller;

import com.example.jee.printing.platform.dao.MatierGroupeDAO;
import com.example.jee.printing.platform.model.MatierGroupe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MatierGroupeManagementServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MatierGroupeDAO matierGroupeDAO = new MatierGroupeDAO();
        List<MatierGroupe> matierGroupeList = matierGroupeDAO.findAll();

        request.setAttribute("matierGroupeList", matierGroupeList);
        request.getRequestDispatcher("/views/matierGroupeManagement.jsp").forward(request, response);
    }
}
