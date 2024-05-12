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

        String[] groupIds = request.getParameterValues("groupIds");

        if (groupIds != null && groupIds.length > 0) {
            int[] groupIdsInt = Arrays.stream(groupIds)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            List<Matier> commonMatiers = matierDAO.findCommonMatiersByGroupIds(groupIdsInt);

            if (commonMatiers.isEmpty()) {
                out.println("<option value=\"\">No common Matiers found</option>");
            } else {
                StringBuilder options = new StringBuilder();
                for (Matier matier : commonMatiers) {
                    options.append("<option value=\"").append(matier.getId()).append("\">")
                            .append(matier.getName()).append("</option>");
                }

                out.println(options.toString());
            }
        } else {
            out.println("<option value=\"\">Sélectionnez un groupe</option>");
        }
    }

}
