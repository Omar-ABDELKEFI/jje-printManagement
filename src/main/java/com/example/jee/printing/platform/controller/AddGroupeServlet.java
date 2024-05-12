package com.example.jee.printing.platform.controller;

import com.example.jee.printing.platform.dao.GroupeDAO;
import com.example.jee.printing.platform.model.Groupe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddGroupeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int numStudents = Integer.parseInt(request.getParameter("numStudents"));

        Groupe groupe = new Groupe();
        groupe.setName(name);
        groupe.setNumStudents(numStudents);

        GroupeDAO groupeDAO = new GroupeDAO();
        groupeDAO.addGroupe(groupe);

        response.sendRedirect("GroupeManagementServlet");
    }
}
