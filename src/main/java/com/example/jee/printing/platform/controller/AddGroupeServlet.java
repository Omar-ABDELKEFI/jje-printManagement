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
        // Retrieve group details from request parameters
        String name = request.getParameter("name");
        int numStudents = Integer.parseInt(request.getParameter("numStudents"));

        // Create a new group object
        Groupe groupe = new Groupe();
        groupe.setName(name);
        groupe.setNumStudents(numStudents);

        // Add the group to the database
        GroupeDAO groupeDAO = new GroupeDAO();
        groupeDAO.addGroupe(groupe);

        // Redirect back to the page after adding the group
        response.sendRedirect("GroupeManagementServlet");
    }
}
