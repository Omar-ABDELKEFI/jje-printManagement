package mjjs.controller;

import mjjs.model.Task;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AgentDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Dummy data for demonstration
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("John Doe", 10, "2024-04-25", "Document1.pdf"));
        tasks.add(new Task("Alice Smith", 15, "2024-04-26", "Document2.pdf"));
        tasks.add(new Task("Bob Johnson", 20, "2024-04-27", "Document3.pdf"));

        // Pass the dummy data to the JSP page
        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("agentDashboard.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handling post requests if any
    }
}
