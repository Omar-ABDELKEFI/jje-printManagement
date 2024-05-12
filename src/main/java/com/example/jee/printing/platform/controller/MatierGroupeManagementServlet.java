package com.example.jee.printing.platform.controller;

import com.example.jee.printing.platform.dao.MatierGroupeDAO;
import com.example.jee.printing.platform.model.MatierGroupe;

import com.example.jee.printing.platform.dao.MatierDAO;
import com.example.jee.printing.platform.model.Matier;

import com.example.jee.printing.platform.dao.GroupeDAO;
import com.example.jee.printing.platform.model.Groupe;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MatierGroupeManagementServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MatierGroupeDAO matierGroupeDAO = new MatierGroupeDAO();
        List<MatierGroupe> matierGroupeList = matierGroupeDAO.findAllWithNames();

        MatierDAO matierDAO = new MatierDAO();
        List<Matier> matierList = matierDAO.findAll();

        GroupeDAO groupeDAO = new GroupeDAO();
        List<Groupe> groupeList = groupeDAO.findAll();

        request.setAttribute("matierList", matierList);
        request.setAttribute("groupeList", groupeList);

        request.setAttribute("matierGroupeList", matierGroupeList);
        request.getRequestDispatcher("/views/matierGroupeManagement.jsp").forward(request, response);
    }
}
