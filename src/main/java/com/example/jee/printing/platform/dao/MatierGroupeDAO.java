package com.example.jee.printing.platform.dao;

import com.example.jee.printing.platform.model.MatierGroupe;
import com.example.jee.printing.platform.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatierGroupeDAO {

    public void addMatierGroupe(MatierGroupe matierGroupe) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionUtils.getConnection();
            String query = "INSERT INTO MatierGroupe (matier_id, groupe_id) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, matierGroupe.getMatierId());
            statement.setInt(2, matierGroupe.getGroupeId());
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

    public MatierGroupe findById(int matierGroupeId) {
        MatierGroupe matierGroupe = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtils.getConnection();
            String query = "SELECT * FROM MatierGroupe WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, matierGroupeId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int matierId = resultSet.getInt("matier_id");
                int groupeId = resultSet.getInt("groupe_id");

                matierGroupe = new MatierGroupe(matierGroupeId, matierId, groupeId);
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

        return matierGroupe;
    }

    public List<MatierGroupe> findAll() {
        List<MatierGroupe> matierGroupes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtils.getConnection();
            String query = "SELECT * FROM MatierGroupe";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int matierGroupeId = resultSet.getInt("id");
                int matierId = resultSet.getInt("matier_id");
                int groupeId = resultSet.getInt("groupe_id");

                MatierGroupe matierGroupe = new MatierGroupe(matierGroupeId, matierId, groupeId);
                matierGroupes.add(matierGroupe);
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

        return matierGroupes;
    }

    public void updateMatierGroupe(MatierGroupe matierGroupe) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionUtils.getConnection();
            String query = "UPDATE MatierGroupe SET matier_id = ?, groupe_id = ? WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, matierGroupe.getMatierId());
            statement.setInt(2, matierGroupe.getGroupeId());
            statement.setInt(3, matierGroupe.getId());
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

    public void deleteMatierGroupe(int matierGroupeId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionUtils.getConnection();
            String query = "DELETE FROM MatierGroupe WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, matierGroupeId);
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
}
