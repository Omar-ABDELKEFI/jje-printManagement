package com.example.jee.printing.platform.dao;

import com.example.jee.printing.platform.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrintMatierGroupeDAO {
    public void save(int printRequestId, int groupId, int matierId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionUtils.getConnection();
            String query = "INSERT INTO PrintMatierGroupe (print_request_id, matier_groupe_id) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, printRequestId);
            statement.setInt(2, groupId); // Change this to appropriate column name

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Other DAO methods (findById, update, delete, etc.)
}
