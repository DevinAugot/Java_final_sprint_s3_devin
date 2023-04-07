package com.keyin.model;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;

    public UserDao() {
    }

    public boolean createUser(User user) throws SQLException {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        String query = "INSERT INTO users (first_name, last_name, email, password, is_doctor, doctor_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail());
        statement.setString(4, hashedPassword);
        statement.setBoolean(5, user.isDoctor());
        statement.setInt(6, user.getId());

        int rowsAffected = statement.executeUpdate();

        return rowsAffected > 0;
    }

    public User getUserById(int id) throws SQLException {
        User user = null;

        String query = "SELECT * FROM users WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getBoolean("is_doctor")

            );
        }

        return user;
    }

    public User getUserByEmail(String email) throws SQLException {
        User user = null;

        String query = "SELECT * FROM users WHERE email = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getBoolean("is_doctor")

            );
        }

        return user;
    }

    public boolean updateUser(User user) throws SQLException {
        String query = "UPDATE users " +
                "SET first_name = ?, last_name = ?, email = ?, is_doctor = ?, doctor_id = ? " +
                "WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail());
        statement.setBoolean(4, user.isDoctor());
        statement.setInt(5, user.getId());


        int rowsAffected = statement.executeUpdate();

        return rowsAffected > 0;
    }

    public boolean deleteUser(int id) throws SQLException {
        String query = "DELETE FROM users WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        int rowsAffected = statement.executeUpdate();

        return rowsAffected > 0;
    }

    public boolean verifyPassword(String email, String password) throws SQLException {
        String query = "SELECT password FROM users WHERE email = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String hashedPassword = resultSet.getString("password");
            return BCrypt.checkpw(password, hashedPassword);
        }

        return false;
    }
    public List<User> getPatientsByDoctorId(int doctorId) throws SQLException {
        List<User> patients = new ArrayList<>();

        String query = "SELECT * FROM users WHERE is_doctor = 0 AND doctor_id = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, doctorId);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            User user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getBoolean("is_doctor")
            );
            patients.add(user);
        }

        return patients;
    }

    public Doctor getDoctorById(int id) throws SQLException {
        Doctor doctor = null;

        String query = "SELECT * FROM users WHERE id = ? AND is_doctor = true";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            doctor = new Doctor(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getBoolean("is_doctor"),
                    resultSet.getString("medical_license_number"),
                    resultSet.getString("specialization")
            );

        }

        return doctor;
    }

}
