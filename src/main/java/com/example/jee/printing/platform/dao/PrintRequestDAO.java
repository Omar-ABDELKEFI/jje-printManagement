package com.example.jee.printing.platform.dao;

import com.example.jee.printing.platform.model.PrintRequest;
import com.example.jee.printing.platform.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrintRequestDAO {
    public int save(PrintRequest printRequest) {
        int printRequestId = -1;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtils.getConnection();
            String query = "INSERT INTO PrintRequest (user_id, numStudents, document) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, printRequest.getUserId());
            statement.setInt(2, printRequest.getNumStudents());
            statement.setBlob(3, printRequest.getDocument());
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                printRequestId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return printRequestId;
    }

    public void saveGroupAssociation(int printRequestId, int groupId) {
        // Logic to save association between print request and group
    }

    public void saveMatierAssociation(int printRequestId, int matierId) {
        // Logic to save association between print request and matier
    }

    // Other DAO methods (findById, update, delete, etc.)
}
