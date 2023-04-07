package com.keyin.model;
import com.keyin.model.HealthDataDao;
import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;

public class DoctorPortalDao {
    private UserDao userDao;
    private HealthDataDao healthDataDao;

    public DoctorPortalDao() {
        userDao = new UserDao();
        healthDataDao = new HealthDataDao();
    }

    public Doctor getDoctorById(int doctorId) throws SQLException {
        return userDao.getDoctorById(doctorId);
    }

    public List<User> getPatientsByDoctorId(int doctorId) throws SQLException {
        return userDao.getPatientsByDoctorId(doctorId);
    }

    public List<HealthData> getHealthDataByPatientId(int patientId) throws SQLException {
        return healthDataDao.getHealthDataByUserId(patientId);
    }

}
