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
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// @WebServlet("/printRequest")
// @MultipartConfig
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the list of groups and subjects from the database
        List<Groupe> groups = groupeDAO.findAll();
        List<Matier> matiers = matierDAO.findAll();

        // Set the lists in the request attributes
        request.setAttribute("groups", groups);
        request.setAttribute("matiers", matiers);

        // Forward the request to the JSP page
        request.getRequestDispatcher("/views/printRequestForm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        
        String[] groupIds = request.getParameterValues("groupIds");
        String[] matierIds = request.getParameterValues("matierIds");
        LocalDateTime arrivalDateTime = LocalDateTime.parse(request.getParameter("arrivalDateTime"));
        Part document = request.getPart("document");
        int numStudents = Integer.parseInt(request.getParameter("numStudents"));

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

        // Save the associated groups and subjects
        List<Integer> groupeIds = new ArrayList<>();
        List<Integer> matierIdsd = new ArrayList<>();

        for (String groupId : groupIds) {
            int groupIdInt = Integer.parseInt(groupId);
            groupeIds.add(groupIdInt);
            printRequestDAO.saveGroupAssociation(printRequestId, groupIdInt);
        }

        for (Integer matierId : matierIdsd) {
            int matierIdInt = matierId;
            matierIdsd.add(matierIdInt);
            printRequestDAO.saveMatierAssociation(printRequestId, matierIdInt);
        }

        // Save the associations in the PrintMatierGroupe table
        for (int groupeId : groupeIds) {
            for (int matierId : matierIdsd) {
                printMatierGroupeDAO.save(printRequestId, groupeId, matierId);
            }
        }

        // Redirect to a success page or show a success message
        response.sendRedirect("success.jsp");
    }
}