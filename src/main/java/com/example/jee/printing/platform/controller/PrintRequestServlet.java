package com.example.jee.printing.platform.controller;

import com.example.jee.printing.platform.dao.GroupeDAO;
import com.example.jee.printing.platform.dao.MatierDAO;
import com.example.jee.printing.platform.dao.PrintMatierGroupeDAO;
import com.example.jee.printing.platform.dao.PrintRequestDAO;
import com.example.jee.printing.platform.model.Groupe;
import com.example.jee.printing.platform.model.Matier;
import com.example.jee.printing.platform.model.PrintRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PrintRequestServlet extends HttpServlet {

    private GroupeDAO groupeDAO;
    private MatierDAO matierDAO;
    private PrintRequestDAO printRequestDAO;
    private PrintMatierGroupeDAO printMatierGroupeDAO;

    @Override
    public void init() throws ServletException {
        groupeDAO = new GroupeDAO();
        matierDAO = new MatierDAO();
        printRequestDAO = new PrintRequestDAO();
        printMatierGroupeDAO = new PrintMatierGroupeDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the list of groups from the database
        List<Groupe> groups = groupeDAO.findAll();

        // Set the list of groups in the request attribute
        request.setAttribute("groups", groups);

        // Forward the request to the JSP page
        request.getRequestDispatcher("/views/printRequestForm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String[] groupIds = request.getParameterValues("groupIds");
        String[] matierIds = request.getParameterValues("matierIds");
        System.out.println("fffffffff");
        Part document = request.getPart("document");
        int numStudents = Integer.parseInt(request.getParameter("numStudents"));

        // Part datePart = request.getPart("arrivalDateTime");
        // System.out.println(datePart);

        System.out.println(request.getParameter("arrivalDateTime"));
        LocalDateTime arrivalDateTime = LocalDateTime.parse(request.getParameter("arrivalDateTime"));

        // Assuming the logged-in user's ID is 1
        int loggedInUserId = 1;

        // Create a new PrintRequest object
        PrintRequest printRequest = new PrintRequest();
        printRequest.setUserId(loggedInUserId);
        printRequest.setNumStudents(numStudents);
        printRequest.setArrivalDateTime(arrivalDateTime);

        // Save the document file
        InputStream documentInputStream = document.getInputStream();
        printRequest.setDocument(documentInputStream);

        // Save the print request to the database
        int printRequestId = printRequestDAO.save(printRequest);

        printRequestDAO.saveGroupMatierAssociation(groupIds, matierIds, printRequestId);

  
    }

}
