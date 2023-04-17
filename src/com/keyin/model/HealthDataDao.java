package com.keyin.model;

import java.sql.*;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

public class HealthDataDao {
    /**
     * I didn't use a createHealthData method because I approached it from a different angle based on a user input menu
     * , it works just as well and inputs that information in my SQL DB
     */


    public boolean updateHealthData(Connection conn, HealthData healthData) throws SQLException {
        PreparedStatement statement = null;

        try {
            String query = "UPDATE health_data SET user_id = ?, weight = ?, height = ?, steps = ?, heart_rate = ?, date = ? WHERE id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, healthData.getUserId());
            statement.setDouble(2, healthData.getWeight());
            statement.setDouble(3, healthData.getHeight());
            statement.setInt(4, healthData.getSteps());
            statement.setInt(5, healthData.getHeartRate());
            statement.setDate(6, healthData.getDate());
            statement.setInt(7, healthData.getId());
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public boolean deleteHealthData(Connection conn, int id) throws SQLException {
        PreparedStatement statement = null;
        boolean deleted = false;

        try {
            String query = "DELETE FROM health_data WHERE id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                deleted = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }

        return deleted;
    }

    public HealthData getHealthDataByUserId(Connection conn, int id) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        HealthData healthData = null;

        try {
            String query = "SELECT * FROM health_data WHERE id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                healthData = new HealthData(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getDouble("weight"),
                        resultSet.getDouble("height"),
                        resultSet.getInt("steps"),
                        resultSet.getInt("heart_rate"),
                        resultSet.getDate("date")
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return healthData;
    }


    public int getStepsById(Connection conn, int id) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int steps = 0;

        try {
            String query = "SELECT steps FROM health_data WHERE id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                steps = resultSet.getInt("steps");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return steps;
    }
}
