package com.example.jee.printing.platform.dao;

import com.example.jee.printing.platform.model.PrintRequest;
import com.example.jee.printing.platform.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

public class PrintRequestDAO {
    public int save(PrintRequest printRequest) {
        int printRequestId = -1;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            connection = ConnectionUtils.getConnection();
            String query = "INSERT INTO PrintRequest (user_id, numStudents, document,fileName,arrivalDateTime) VALUES (?, ?, ?,?,?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, printRequest.getUserId());
            statement.setInt(2, printRequest.getNumStudents());
            statement.setBlob(3, printRequest.getDocument());
            statement.setString(4, printRequest.getFileName());
            statement.setTimestamp(5, java.sql.Timestamp.valueOf(printRequest.getArrivalDateTime()));

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                printRequestId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return printRequestId;
    }

    public void saveGroupMatierAssociation(String[] groupIds, String[] matierIds, int printRequestId) {
        Connection connection = null;
        PreparedStatement statement = null;
        System.out.print("ffffffffffff");
       
        try {
            connection = ConnectionUtils.getConnection();
            String query = "INSERT INTO PrintMatierGroupe (print_request_id, matier_groupe_id) VALUES (?, " +
                    "(SELECT mg.id FROM MatierGroupe mg WHERE mg.matier_id = ? AND mg.groupe_id = ?))";
            statement = connection.prepareStatement(query);

            for (String matierId : matierIds) {
                for (String groupId : groupIds) {
                    statement.setInt(1, printRequestId);
                    statement.setInt(2, Integer.parseInt(matierId));
                    statement.setInt(3, Integer.parseInt(groupId));
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
