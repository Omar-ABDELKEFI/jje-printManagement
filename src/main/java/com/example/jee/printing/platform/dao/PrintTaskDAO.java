package com.example.jee.printing.platform.dao;

import com.example.jee.printing.platform.model.Task;
import com.example.jee.printing.platform.utils.ConnectionUtils;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrintTaskDAO {
    public List<Task> getAgentTasks() {
        List<Task> tasks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtils.getConnection();
            String query = "SELECT pr.user_id, u.email AS teacher_name, pr.numStudents AS num_copies, pr.arrivalDateTime AS reception_date, pr.document, pr.fileName FROM PrintRequest pr JOIN User u ON pr.user_id = u.id";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String teacherName = resultSet.getString("teacher_name");
                int numCopies = resultSet.getInt("num_copies");
                String receptionDate = resultSet.getString("reception_date");
                String document = resultSet.getString("document");
                String fileName = resultSet.getString("fileName");
                Task task = new Task(teacherName, numCopies, receptionDate, document, fileName);
                tasks.add(task);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return tasks;
    }
}
