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


}


