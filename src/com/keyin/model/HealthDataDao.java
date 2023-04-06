package com.keyin.model;

import java.util.List;

public class HealthDataDao {
    public boolean createHealthData(HealthData healthData) { /* insert health data into database */ }
    public HealthData getHealthDataById(int id) { /* get health data by id from database */ }
    public List<HealthData> getHealthDataByUserId(int userId) { /* get health data by user id from database */ }
    public boolean updateHealthData(HealthData healthData) { /* update health data in the database */ }
    public boolean deleteHealthData(int id) { /* delete health data from the database */ }
}
