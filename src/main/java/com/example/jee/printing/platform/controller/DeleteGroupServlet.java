package com.example.jee.printing.platform.controller;

import com.example.jee.printing.platform.dao.GroupeDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));

        GroupeDAO groupeDAO = new GroupeDAO();
        groupeDAO.deleteGroupe(groupId);

        response.sendRedirect("GroupeManagementServlet"); 
    }
}
