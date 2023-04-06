package com.keyin.model;

import java.util.List;

public class DoctorPortalDao {
    private UserDao userDao;
    private HealthDataDao healthDataDao;

    public DoctorPortalDao() {
        userDao = new UserDao();
        healthDataDao = new HealthDataDao();
    }

    public Doctor getDoctorById(int doctorId) {
        // Implement this method
    }

    public List<User> getPatientsByDoctorId(int doctorId) {
        // Implement this method
    }

    public List<HealthData> getHealthDataByPatientId(int patientId) {
        // Implement this method
    }

    // Add more methods for other doctor-specific tasks
}

