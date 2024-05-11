package com.example.jee.printing.platform.dao;

import com.example.jee.printing.platform.model.Matier;
import com.example.jee.printing.platform.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatierDAO {
    public List<Matier> findAll() {
        List<Matier> matiers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtils.getConnection();
            String query = "SELECT * FROM Matier";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                Matier matier = new Matier(id, name);
                matiers.add(matier);
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

        return matiers;
    }

    public List<Matier> findByGroupId(int groupId) {
        List<Matier> matiers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtils.getConnection();
            String query = "SELECT m.id, m.name FROM Matier m " +
                           "JOIN MatierGroupe mg ON m.id = mg.matier_id " +
                           "WHERE mg.groupe_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, groupId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                Matier matier = new Matier(id, name);
                matiers.add(matier);
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

        return matiers;
    }

    public List<Matier> findCommonMatiersByGroupIds(int[] groupIds) {
        List<Matier> commonMatiers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
    
        try {
            connection = ConnectionUtils.getConnection();
    
            // Construct the SQL query with a join on the MatierGroupe table
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT m.id, m.name ");
            queryBuilder.append("FROM Matier m ");
            queryBuilder.append("INNER JOIN MatierGroupe mg ON m.id = mg.matier_id ");
            queryBuilder.append("WHERE mg.groupe_id IN (");
    
            // Build the IN clause with the group IDs
            for (int i = 0; i < groupIds.length; i++) {
                queryBuilder.append("?");
                if (i < groupIds.length - 1) {
                    queryBuilder.append(",");
                }
            }
            queryBuilder.append(") ");
    
            // Add a HAVING clause to filter for common matiers
            queryBuilder.append("GROUP BY m.id, m.name ");
            queryBuilder.append("HAVING COUNT(DISTINCT mg.groupe_id) = ?");
    
            String query = queryBuilder.toString();
            statement = connection.prepareStatement(query);
    
            // Set the parameter values (group IDs and the count)
            int paramIndex = 1;
            for (int groupId : groupIds) {
                statement.setInt(paramIndex++, groupId);
            }
            statement.setInt(paramIndex, groupIds.length);
    
            resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Matier matier = new Matier(id, name);
                commonMatiers.add(matier);
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
    
        return commonMatiers;
    }

    // Other DAO methods (findById, save, update, delete)
}