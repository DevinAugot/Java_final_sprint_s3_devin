package com.keyin.client;

import java.sql.*;
import java.sql.Connection;

public class DatabaseConnection {
    public Connection PostgresClient(String dbName, String user, String password) {

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            // Load the PostgresSQL Driver
            Class.forName("org.postgresql.Driver");

            //Establish a connection to the database
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, user, password);
            if (connection != null) {
                System.out.println("connection established");
            } else {
                System.out.println("Connection failed");
            }

            // catch any exception errors
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);

        }
        return connection;
    }
    public void CreateUsersTable(Connection connection, String tableName) {
        Statement statement;
        try {
            // Execute SQL query
            String query = "CREATE TABLE " + tableName + "(" +
                    "id SERIAL PRIMARY KEY," +
                    "first_name VARCHAR(50) NOT NULL," +
                    "last_name VARCHAR(50) NOT NULL," +
                    "email VARCHAR(100) UNIQUE NOT NULL," +
                    "password VARCHAR(255) NOT NULL," +
                    "is_doctor BOOLEAN NOT NULL" +
                    ")";


            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table" + tableName + " Was Created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createDoctorPatientTable(Connection connection, String table_name) {
        Statement statement;
        try {
            // Execute SQL query
            String query = "CREATE TABLE " + table_name + " ("
                    + "doctor_id INT NOT NULL,"
                    + "patient_id INT NOT NULL,"
                    + "PRIMARY KEY (doctor_id, patient_id),"
                    + "FOREIGN KEY (doctor_id) REFERENCES users(id),"
                    + "FOREIGN KEY (patient_id) REFERENCES users(id)"
                    + ")";

            statement = connection.createStatement();
            statement.executeUpdate(query);

            System.out.println("Table Created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createHealthDataTable(Connection connection, String tableName) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "CREATE TABLE " + tableName + " (" +
                    "id SERIAL PRIMARY KEY," +
                    "user_id INT NOT NULL," +
                    "weight DECIMAL(5,2) NOT NULL," +
                    "height DECIMAL(5,2) NOT NULL," +
                    "steps INT NOT NULL," +
                    "heart_rate INT NOT NULL," +
                    "date DATE NOT NULL," +
                    "FOREIGN KEY (user_id) REFERENCES users(id)" +
                    ")";
            statement.executeUpdate(query);
            System.out.println("Table " + tableName + " created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating table " + tableName + ": " + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
        }
    }
    public void createMedicineRemindersTable(Connection connection, String tableName) {
        Statement statement;
        try {
            // Execute SQL query
            String query = "CREATE TABLE " + tableName + " (id SERIAL PRIMARY KEY, user_id INT NOT NULL, " +
                    "medicine_name VARCHAR(100) NOT NULL, dosage VARCHAR(50) NOT NULL, schedule VARCHAR(100) NOT NULL, " +
                    "start_date DATE NOT NULL, end_date DATE NOT NULL, FOREIGN KEY (user_id) REFERENCES users(id))";

            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table" + tableName + " was created.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createRecommendationsTable(Connection connection, String tableName) {
        Statement statement = null;

        try {
            statement = connection.createStatement();
            String query = "CREATE TABLE " + tableName + " (" +
                    "id SERIAL PRIMARY KEY, " +
                    "user_id INT NOT NULL, " +
                    "recommendation_text TEXT NOT NULL, " +
                    "date DATE NOT NULL, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id)" +
                    ")";

            statement.executeUpdate(query);
            System.out.println("Table " + tableName + " was created.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void insertIntoUserTable(Connection conn,int id, String firstName, String lastName, String email, String password, boolean isDoctor) {
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO users (id, first_name, last_name, email, password, is_doctor) VALUES (?, ?, " +
                    "?, " +
                    "?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, email);
            statement.setString(5, password);
            statement.setBoolean(6,isDoctor);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User has been added to database");
            } else {
                System.out.println("ERROR: User could not be added to database, Check SQL syntax");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void insertIntoDoctorPatientTable(Connection conn,int doctor_id, int patient_id) {
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO doctor_patient (doctor_id, patient_id) " +
                    "VALUES " +
                    "(?, ?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1, doctor_id);
            statement.setInt(2, patient_id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Doctor & patient record has been added to database");
            } else {
                System.out.println("ERROR: Doctor & patient record could not be added to database, Check SQL syntax");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    public void insertIntoHealthDataTable(Connection conn,int id, int userId, double weight, double height, int steps,
                                          int heart_rate, Date date) {
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO health_data (id, user_id, weight, height, steps, heart_rate, date) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.setInt(2, userId);
            statement.setDouble(3, weight);
            statement.setDouble(4,height);
            statement.setInt(5, steps);
            statement.setInt(6, heart_rate);
            statement.setDate(7,date);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Health Data  has been added to database");
            } else {
                System.out.println("ERROR: Health Data record could not be added to database, Check SQL syntax");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }


    }
    public void insertIntoMedReminderTable(Connection conn,int id, int user_id, String medicine_name, String dosage,
                                           Date schedule, Date start_date, Date end_date) {
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO medicine_reminders (id, user_id, weight, height, steps, heart_rate) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.setInt(2, user_id);
            statement.setString(3, medicine_name);
            statement.setString(4,dosage);
            statement.setDate(5, schedule);
            statement.setDate(6, start_date);
            statement.setDate(7,end_date);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Health Data  has been added to database");
            } else {
                System.out.println("ERROR: Health Data record could not be added to database, Check SQL syntax");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

public void insertIntoRecommendationsTable(Connection conn,int id, int user_id, String recommendation_text, Date date) {
    PreparedStatement statement = null;
    try {
        String query = "INSERT INTO recommendations (id, user_id, recommendation_text, date) " +
                "VALUES " +
                "(?, ?, ?, ?)";
        statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        statement.setInt(2, user_id);
        statement.setString(3, recommendation_text);
        statement.setDate(4, date);


        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Recommendations has been added to database");
        } else {
            System.out.println("ERROR: Recommendations record could not be added to database, Check SQL syntax");
        }
    } catch (Exception e) {
        System.out.println(e);
    } finally {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
    public ResultSet queryDatabase(Connection conn,String query) {
        ResultSet rs = null;
        try {
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

}


