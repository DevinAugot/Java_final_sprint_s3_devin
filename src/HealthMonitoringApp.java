import com.keyin.client.DatabaseConnection;
import com.keyin.model.Doctor;
import com.keyin.model.HealthData;
import com.keyin.model.MedicineReminder;
import com.keyin.model.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HealthMonitoringApp {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.PostgresClient("health_management", "postgres", "Keyin2021");

        // Create Tables for information storage (Have all tables created)
//        db.CreateUsersTable(conn,"Users");
//        db.createDoctorPatientTable(conn,"doctor_patient");
//        db.createHealthDataTable(conn,"health_data");
//        db.createMedicineRemindersTable(conn,"medicine_reminders");
//        db.createRecommendationsTable(conn,"recommendations");


        // SQL QUERY'S
        String GET_ALL_USERS = "SELECT * FROM Users";
        String GET_ALL_DOC_PATIENT_INFO = "SELECT * FROM doctor_patient";
        String GET_ALL_HEALTH_DATA = "SELECT * FROM health_data";
        String GET_ALL_REMINDERS = "SELECT * FROM medicine_reminders";
        String GET_ALL_RECOMMENDATIONS = "SELECT * FROM recommendations";

        // initializing array lists
        List<User> listOfUsers = new ArrayList<>();
        List<Doctor> listOfDoctors = new ArrayList<>();
        List<HealthData> listOfHealthData = new ArrayList<>();
        List<MedicineReminder> listOfReminders = new ArrayList<>();

        // user table inserts

        // Patients
//        db.insertIntoUserTable(conn,1,"Devin","Augot","daugot22@gmail.com", "dev123",false);
//        db.insertIntoUserTable(conn,2,"Allison","Butler","abc123@gmail.com","Allison2023",false);

//         // Doctors
//        db.insertIntoUserTable(conn,3,"Ebuka","Ameafula","ebuka@owlya.com","ebuka123",true);
//        db.insertIntoUserTable(conn,4,"Lynn","Butler","lynnbutler@gmail.com","lynn123",true);

          // Insert into doc-patient table
//          db.insertIntoDoctorPatientTable(conn,3,1);
//          db.insertIntoDoctorPatientTable(conn,4,2);

        // Insert into healthData table
        db.insertIntoHealthDataTable(conn,1,1,195.00,6.00,12000,68, Date.valueOf("2023-04-23"));

    }
}