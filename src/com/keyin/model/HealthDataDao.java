package com.keyin.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HealthDataDao {
 // may have to change this 
        private Connection connection;

        // constructor to initialize the connection
        public HealthDataDao(Connection connection) {
            this.connection = connection;
        }

        // retrieve all health data for a specific user
        public List<HealthData> getAllHealthDataForUser(int userId) throws SQLException {
            String query = "SELECT * FROM health_data WHERE user_id = ?";

            List<HealthData> healthDataList = new ArrayList<>();

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        double weight = resultSet.getDouble("weight");
                        double height = resultSet.getDouble("height");
                        int steps = resultSet.getInt("steps");
                        int heartRate = resultSet.getInt("heart_rate");
                        Date date = resultSet.getDate("date");

                        HealthData healthData = new HealthData(id, userId, weight, height, steps, heartRate, date);
                        healthDataList.add(healthData);
                    }
                }
            }

            return healthDataList;
        }


    public HealthData getHealthDataById(int id) { /* get health data by id from database */ }
    public List<HealthData> getHealthDataByUserId(int userId) { /* get health data by user id from database */ }
    public boolean updateHealthData(HealthData healthData) { /* update health data in the database */ }
    public boolean deleteHealthData(int id) { /* delete health data from the database */ }
}
