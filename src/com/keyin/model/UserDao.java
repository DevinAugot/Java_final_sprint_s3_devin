package com.keyin.model;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDao {
    public boolean createUser(User user) {
        /* insert user into database */
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        // Prepare the SQL query

        // Database logic to insert data using PREPARED Statement

    }
    public User getUserById(int id) { /* get user by id from database */
        User user = null;

        // Prepare the SQL query
        // Database logic to get data by ID Using Prepared Statement

    }

    public User getUserByEmail(String email) { /* get user by email from database */
        User user = null;

        // Prepare the SQL query
        // Database logic to get data by ID Using Prepared Statement

    }


    public boolean updateUser(User user) {
        // Prepare the SQL query
        // Database logic to get update user Using Prepared Statement
    }
    public boolean deleteUser(int id) { /* delete user from the database */
        // Prepare the SQL query
        // Database logic to delete user
    }

    public boolean verifyPassword (String email, String password){
        String query = "SELECT password FROM users WHERE email = ?";
        //Implement logic to retrieve password using the Bcrypt
    }

}
