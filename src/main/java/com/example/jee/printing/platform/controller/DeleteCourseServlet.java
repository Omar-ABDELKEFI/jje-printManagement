package com.example.jee.printing.platform.controller;

import com.example.jee.printing.platform.dao.MatierDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int MatierId = Integer.parseInt(request.getParameter("matierId"));

        MatierDAO MatierDAO = new MatierDAO();
        MatierDAO.deleteMatier(MatierId);

        response.sendRedirect("MatierManagementServlet"); 
        
           }
}
