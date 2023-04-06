import com.keyin.client.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
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


    }
}