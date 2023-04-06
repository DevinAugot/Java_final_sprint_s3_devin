package com.keyin.client;

public class DatabaseConnection {
    import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    }

}
