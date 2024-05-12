package com.example.jee.printing.platform.controller;

import com.example.jee.printing.platform.dao.GroupeDAO;
import com.example.jee.printing.platform.model.Groupe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GroupeManagementServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupeDAO groupeDAO = new GroupeDAO();
        List<Groupe> groupeList = groupeDAO.findAll();

        request.setAttribute("groupeList", groupeList);
        request.getRequestDispatcher("/views/groupeManagement.jsp").forward(request, response);
    }

}
