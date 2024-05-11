package com.example.jee.printing.platform.dao;

import com.example.jee.printing.platform.model.Groupe;
import com.example.jee.printing.platform.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupeDAO {
    public void addGroupe(Groupe groupe) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionUtils.getConnection();
            String query = "INSERT INTO Groupe (name, numStudents) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, groupe.getName());
            statement.setInt(2, groupe.getNumStudents());
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

    public Groupe findById(int groupId) {
        Groupe groupe = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtils.getConnection();
            String query = "SELECT * FROM Groupe WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, groupId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int numStudents = resultSet.getInt("numStudents");

                groupe = new Groupe(groupId, name, numStudents);
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

        return groupe;
    }

    public List<Groupe> findAll() {
        List<Groupe> groupes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtils.getConnection();
            String query = "SELECT * FROM Groupe";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int groupId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int numStudents = resultSet.getInt("numStudents");

                Groupe groupe = new Groupe(groupId, name, numStudents);
                groupes.add(groupe);
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

        return groupes;
    }

    public void updateGroupe(Groupe groupe) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionUtils.getConnection();
            String query = "UPDATE Groupe SET name = ?, numStudents = ? WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, groupe.getName());
            statement.setInt(2, groupe.getNumStudents());
            statement.setInt(3, groupe.getId());
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

    public void deleteGroupe(int groupId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionUtils.getConnection();
            String query = "DELETE FROM Groupe WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, groupId);
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
