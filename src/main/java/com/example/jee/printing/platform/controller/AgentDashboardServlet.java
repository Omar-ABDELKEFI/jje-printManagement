package com.example.jee.printing.platform.controller;

import com.example.jee.printing.platform.dao.PrintTaskDAO;
import com.example.jee.printing.platform.model.Task;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AgentDashboardServlet extends HttpServlet {
    private PrintTaskDAO printTaskDAO;

    @Override
    public void init() throws ServletException {
        printTaskDAO = new PrintTaskDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les tâches de l'agent de tirage depuis la base de données en utilisant PrintTaskDAO
        List<Task> tasks = printTaskDAO.getAgentTasks();
        // Passer les tâches à la page JSP
        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("/views/agentDashboard.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gérer les requêtes POST si nécessaire
    }
}
