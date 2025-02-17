package com.example.jee.printing.platform.controller;

import com.example.jee.printing.platform.dao.GroupeDAO;
import com.example.jee.printing.platform.model.Groupe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditGroupeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        String name = request.getParameter("name");
        int numStudents = Integer.parseInt(request.getParameter("numStudents"));

        GroupeDAO groupeDAO = new GroupeDAO();
        Groupe groupe = groupeDAO.findById(groupId);

        if (groupe != null) {
            groupe.setName(name);
            groupe.setNumStudents(numStudents);
            groupeDAO.updateGroupe(groupe);
        }

        response.sendRedirect("GroupeManagementServlet");
    }
}
