package com.keyin.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorPortalDao {

    private UserDao userDao;
    private HealthDataDao healthDataDao;

    public DoctorPortalDao() {
        userDao = new UserDao();
        healthDataDao = new HealthDataDao();
    }

    public Doctor getDoctorById(Connection conn,int doctorId) throws SQLException {
        return userDao.getDoctorById(conn,doctorId);
    }

    public List<User> getPatientsByDoctorId(Connection conn,int doctorId) throws SQLException {
        return userDao.getPatientsByDoctorId(conn,doctorId);
    }

    public List<HealthData> getHealthDataByPatientId(Connection conn,int patientId) throws SQLException {
        List<HealthData> healthDataList = new ArrayList<>();
        HealthData healthData = healthDataDao.getHealthDataByUserId(conn,patientId);
        healthDataList.add(healthData);
        return healthDataList;
    }



}
