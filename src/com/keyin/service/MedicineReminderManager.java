package com.keyin.service;


import com.keyin.model.MedicineReminder;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * The MedicineReminderManager class should have methods to add reminders, get reminders
 *  1. for a specific user, and
 *  2. get reminders that are DUE for a specific user.
 *
 *  You'll need to integrate this class with your application and database logic to
 *  1. store,
 *  2. update, and
 *  3. delete reminders as needed.
 */




public class MedicineReminderManager {
    //** // Create A Medicine Reminder
    // Get Medicine Reminders By Patient Id
    // Update Medicine Reminder
    // Delete Medincine Reminder

    public static void createMedicineReminder(Connection conn, MedicineReminder medicineReminder) {
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO medicine_reminders (id,user_id, medicine_name, dosage, schedule,start_date," +
                    "end_date) VALUES (?, ?, ?, ?, ?,?,?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1, medicineReminder.getUserId());
            statement.setInt(2, medicineReminder.getUserId());
            statement.setString(3, medicineReminder.getMedicineName());
            statement.setString(4, medicineReminder.getDosage());
            statement.setDate(5, medicineReminder.getSchedule());
            statement.setDate(6, medicineReminder.getStartDate());
            statement.setDate(7, medicineReminder.getEndDate());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Medicine Reminder Added For User With ID" + medicineReminder.getUserId());
            } else {
                System.out.println("Health Data Upload Has Failed");
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

    public static ResultSet getMedicineReminderByUserId(Connection conn, int id) {
        ResultSet rs = null;
        try {
            String sql = "SELECT Mr.*, u.first_name,u.last_name,u.is_doctor\n" + "from medicine_reminders Mr\n" + "INNER Join users u ON Mr.user_id = u.id\n" + "WHERE u.id = ?";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setInt(1, id);
            try {
                rs = prepareStatement.executeQuery();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Medicine Reminder For Patient With ID:" + id);
            System.out.println();
            int reminderCount = 1;
            while (rs.next()) {
                System.out.println(" ");
                System.out.println("Reminder # " + reminderCount++);
                System.out.println("================================");
                int medReminderId = rs.getInt("id");
                int userId = rs.getInt("user_id");
                String medName = rs.getString("medicine_name");
                String dosage = rs.getString("dosage");
                String schedule = rs.getString("schedule");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                System.out.println();
                if (endDate.toLocalDate().isBefore(LocalDateTime.now().toLocalDate())) {
                    System.out.println("Med Reminder Is Now Expired");
                } else {
                    System.out.println("Med Reminder For User: " + fname + " " + lname);
                    System.out.println("User ID: " + userId);
                    System.out.println("Medicine Name: " + medName);
                    System.out.println("Dosage: " + dosage);
                    System.out.println("Schedule: " + schedule);
                    System.out.println("Start Date: " + startDate);
                    System.out.println("End Date: " + endDate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet getMedicineReminderByUserName(Connection conn, String first_name, String last_name) {
        int reminderCount = 1;
        ResultSet rs = null;
        try {
            String sql = "SELECT Mr.*, u.first_name,u.last_name,u.is_doctor\n" + "from medicine_reminders Mr\n" + "INNER Join users u ON Mr.user_id = u.id\n" + "WHERE u.first_name = ? and u.last_name = ?";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setString(1, first_name);
            prepareStatement.setString(2, last_name);
            rs = prepareStatement.executeQuery();
            System.out.println("Medicine Reminder For Patient With Name:" + first_name + " " + last_name);
            while (rs.next()) {
                System.out.println("Reminder # " + reminderCount++);
                System.out.println("================================");
                int medReminderId = rs.getInt("id");
                int userId = rs.getInt("user_id");
                String medName = rs.getString("medicine_name");
                String dosage = rs.getString("dosage");
                String schedule = rs.getString("schedule");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                if (endDate.toLocalDate().isBefore(LocalDateTime.now().toLocalDate())) {
                    System.out.println("Med Reminder Is Now Expired");
                    System.out.println();
                } else {
                    System.out.println("Med Reminder For User: " + fname + " " + lname);
                    System.out.println("User ID: " + userId);
                    System.out.println("Medicine Name: " + medName);
                    System.out.println("Dosage: " + dosage);
                    System.out.println("Schedule: " + schedule);
                    System.out.println("Start Date: " + startDate);
                    System.out.println("End Date: " + endDate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void deleteMedicineReminder(Connection conn, int medicine_reminder_id) {
        PreparedStatement statement = null;
        try {
            String query = "DELETE FROM medicine_reminders WHERE id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, medicine_reminder_id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Medicine Reminder Has Been Deleted ");
            } else {
                System.out.println("Medicine Reminder could not be deleted");
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

    public void updateMedicineReminder(Connection conn, int id, int user_id, String medicine_name, String dosage, String schedule, Date start_date, Date end_date) {
        PreparedStatement statement = null;
        try {
            String query = "update medicine_reminders set user_id = ?, medicine_name = ?, dosage = ?, schedule=?,start_date=? ,end_date=? where id = ? ";
            statement = conn.prepareStatement(query);
            statement.setInt(1, user_id);
            statement.setString(2, medicine_name);
            statement.setString(3, dosage);
            statement.setString(4, schedule);
            statement.setDate(5, start_date);
            statement.setDate(6, end_date);
            statement.setInt(7, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Medicine Reminder has been Updated");
            } else {
                System.out.println("Medicine Reminder could not be Updated");
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
}