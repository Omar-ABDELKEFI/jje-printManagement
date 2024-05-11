package com.example.jee.printing.platform.controller;

import com.example.jee.printing.platform.dao.MatierDAO;
import com.example.jee.printing.platform.model.Matier;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class GetMatiersServlet extends HttpServlet {

    private MatierDAO matierDAO;

    @Override
    public void init() throws ServletException {
        matierDAO = new MatierDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve the group IDs from the request parameters
        String[] groupIds = request.getParameterValues("groupIds");

        if (groupIds != null && groupIds.length > 0) {
            // Convert group IDs to integers
            int[] groupIdsInt = Arrays.stream(groupIds)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            List<Matier> commonMatiers = matierDAO.findCommonMatiersByGroupIds(groupIdsInt);

            // If there are no common matiers, provide a message
            if (commonMatiers.isEmpty()) {
                out.println("<option value=\"\">No common Matiers found</option>");
            } else {
                // Generate HTML options for the common matiers
                StringBuilder options = new StringBuilder();
                for (Matier matier : commonMatiers) {
                    options.append("<option value=\"").append(matier.getId()).append("\">")
                            .append(matier.getName()).append("</option>");
                }

                // Send the generated HTML options as the response
                out.println(options.toString());
            }
        } else {
            out.println("<option value=\"\">No Group IDs Provided</option>");
        }
    }

}
