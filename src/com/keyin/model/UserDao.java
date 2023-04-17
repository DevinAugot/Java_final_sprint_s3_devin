package com.keyin.model;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

        public User getUserById(Connection conn, int id) throws SQLException {
            User user = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                String query = "SELECT * FROM users WHERE id=?";
                statement = conn.prepareStatement(query);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    user.setDoctor(resultSet.getBoolean("is_doctor"));
                    user.setMedicalLicenseNum(resultSet.getString("medical_license_num"));
                    user.setSpecialization(resultSet.getString("specialization"));
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
            }
            return user;
        }


    public User getUserByEmail(Connection conn, String email) throws SQLException {
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM users WHERE email=?";
            statement = conn.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setDoctor(resultSet.getBoolean("is_doctor"));
                user.setMedicalLicenseNum(resultSet.getString("medical_license_num"));
                user.setSpecialization(resultSet.getString("specialization"));
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
        }
        return user;
    }
    public boolean updateUser(Connection conn, User user) {
        PreparedStatement statement = null;
        try {
            String query = "UPDATE users SET first_name=?, last_name=?, email=?, is_doctor=? WHERE id=?";
            statement = conn.prepareStatement(query);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setBoolean(4, user.isDoctor());
            statement.setInt(5, user.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                System.out.println("ERROR: User record could not be updated in database, Check SQL syntax");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public boolean deleteUser(Connection conn, int id) throws SQLException {
        PreparedStatement statement = null;
        try {
            String query = "DELETE FROM users WHERE id=?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User with ID " + id + " has been deleted from the database.");
                return true;
            } else {
                System.out.println("No user with ID " + id + " found in the database.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }


    public LoginResult verifyPassword(Connection conn, String email, String password) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            while (true) {
                String query = "SELECT * FROM users WHERE email = ?";
                statement = conn.prepareStatement(query);
                statement.setString(1, email);
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int userId = resultSet.getInt("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String hashedPassword = resultSet.getString("password");
                    boolean isDoctor = resultSet.getBoolean("is_doctor");
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        User user = new User();
                        System.out.println("Login successful!");
                        return new LoginResult(user, isDoctor);
                    }
                }
                return null;
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
        }
        // user not found
        return null;
    }

    public class LoginResult {
        private final User user;
        private final boolean isDoctor;

        public LoginResult(User user, boolean isDoctor) {
            this.user = user;
            this.isDoctor = isDoctor;
        }

        public String getUser() {
            return user != null ? user.getFirstName() : null;
        }

        public boolean isDoctor() {
            return isDoctor;
        }

        public boolean isSuccess() {
            return user != null;
        }
    }
    public Doctor getDoctorById(Connection conn, int doctorId) throws SQLException {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Doctor doctor = null;

        try {

            String query = "SELECT * FROM users WHERE id = ? AND is_doctor = true";
            statement = conn.prepareStatement(query);
            statement.setInt(1, doctorId);
            resultSet = statement.executeQuery();

            if (resultSet.next() && resultSet.getBoolean("is_doctor")) {
                doctor = new Doctor();
                doctor.setId(resultSet.getInt("id"));
                doctor.setFirstName(resultSet.getString("first_name"));
                doctor.setLastName(resultSet.getString("last_name"));
                doctor.setEmail(resultSet.getString("email"));
                doctor.setPassword(resultSet.getString("password"));
                doctor.setDoctor(resultSet.getBoolean("is_doctor"));
                doctor.setMedicalLicenseNumber(resultSet.getString("medical_license_num"));
                doctor.setSpecialization(resultSet.getString("specialization"));
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

        return doctor;
    }

    public List<User> getPatientsByDoctorId(Connection conn,int doctorId) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> patients = new ArrayList<>();

        try {
            String query = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, u.is_doctor " +
                    "FROM users u " +
                    "JOIN doctor_patient dp ON u.id = dp.patient_id " +
                    "WHERE dp.doctor_id = ? AND u.is_doctor = false";

            statement = conn.prepareStatement(query);
            statement.setInt(1, doctorId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User patient = new User();
                patient.setId(resultSet.getInt("id"));
                patient.setFirstName(resultSet.getString("first_name"));
                patient.setLastName(resultSet.getString("last_name"));
                patient.setEmail(resultSet.getString("email"));
                patients.add(patient);
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

        }

        return patients;
    }


}
