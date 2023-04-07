package com.keyin.model;

import java.sql.*;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

public class HealthDataDao {
//    public boolean createHealthData(HealthData healthData) { /* insert health data into database */ }
//    public HealthData getHealthDataById(int id) { /* get health data by id from database */ }
//    public boolean updateHealthData(HealthData healthData) { /* update health data in the database */ }
//
//    public boolean deleteHealthData(int id) { /* delete health data from the database */ }
//
//

    // this needs to be fixed prepared statement is not correct
public List<HealthData> getHealthDataByUserId(int userId) throws SQLException {
    PreparedStatement statement = null;
    Connection conn = null;
    List<HealthData> healthDataList = new ArrayList<>();

    String query = "SELECT * FROM health_data WHERE user_id = ?";

    statement = conn.prepareStatement(query);
    statement.setInt(1, userId);

    ResultSet resultSet = statement.executeQuery();

    while (resultSet.next()) {
        HealthData healthData = new HealthData(
                resultSet.getInt("id"),
                resultSet.getInt("user_id"),
                resultSet.getDouble("weight"),
                resultSet.getDouble("height"),
                resultSet.getInt("steps"),
                resultSet.getInt("heart_rate"),
                resultSet.getDate("date")
        );
        healthDataList.add(healthData);
    }

    return healthDataList;
}



}

