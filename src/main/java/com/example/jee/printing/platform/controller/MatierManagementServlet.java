package com.example.jee.printing.platform.controller;

import com.example.jee.printing.platform.dao.MatierDAO;
import com.example.jee.printing.platform.model.Matier;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MatierManagementServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MatierDAO matierDAO = new MatierDAO();
        List<Matier> matierList = matierDAO.findAll();

        request.setAttribute("matierList", matierList);
        request.getRequestDispatcher("/views/courseManagement.jsp").forward(request, response);
    }

}
