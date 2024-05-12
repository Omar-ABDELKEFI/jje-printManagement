package com.example.jee.printing.platform.dao;

import com.example.jee.printing.platform.model.Task;
import com.example.jee.printing.platform.utils.ConnectionUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class PrintTaskDAO {
    public List<Task> getAgentTasks() throws IOException {
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
                InputStream inputStream = resultSet.getBinaryStream("document");
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                byte[] pdfBytes = outputStream.toByteArray();
                
                // Encode the byte array as a base64 string
                String pdfBase64 = Base64.getEncoder().encodeToString(pdfBytes);

                String document = pdfBase64;
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
