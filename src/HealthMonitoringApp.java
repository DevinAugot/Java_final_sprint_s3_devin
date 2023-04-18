import com.keyin.client.DatabaseConnection;
import com.keyin.model.*;
import com.keyin.service.RecommendationSystem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.keyin.service.MedicineReminderManager;

public class HealthMonitoringApp {
    public static void main(String[] args) throws SQLException, InterruptedException {
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.PostgresClient("health_management", "postgres", "Keyin2021");

        // Create Tables for information storage (Have all tables created)
        db.CreateUsersTable(conn,"Users");
        db.createDoctorPatientTable(conn,"doctor_patient");
        db.createHealthDataTable(conn,"health_data");
        db.createMedicineRemindersTable(conn,"medicine_reminders");
        db.createRecommendationsTable(conn,"recommendations");


        // SQL QUERY
        String GET_ALL_USERS = "SELECT * FROM Users";


        // Insert into doc-patient table
        db.insertIntoDoctorPatientTable(conn,3,1);
        db.insertIntoDoctorPatientTable(conn,4,2);
        db.insertIntoDoctorPatientTable(conn,4,5);
        db.insertIntoDoctorPatientTable(conn,3,6);
        db.insertIntoDoctorPatientTable(conn,4,7);
        db.insertIntoDoctorPatientTable(conn,3,8);


        // user dao (creating New users)

        UserDao userDao = new UserDao();

        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("my_password");
        user1.setDoctor(false);
        user1.setMedicalLicenseNum("");
        user1.setSpecialization("");
        users.add(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setEmail("jane.doe@example.com");
        user2.setPassword("my_password123");
        user2.setDoctor(false);
        user2.setMedicalLicenseNum("");
        user2.setSpecialization("");
        users.add(user2);

        User user3 = new User();
        user3.setId(3);
        user3.setFirstName("Dr.Jane");
        user3.setLastName("Doe");
        user3.setEmail("jane.doe@gmail.com");
        user3.setPassword("my_password321");
        user3.setDoctor(true);
        user3.setMedicalLicenseNum("123456");
        user3.setSpecialization("Pediatrics");
        users.add(user3);

        User user4 = new User();
        user4.setId(4);
        user4.setFirstName("Dr.Joker");
        user4.setLastName("Smith");
        user4.setEmail("joker.smith@gmail.com");
        user4.setPassword("my_password122");
        user4.setDoctor(true);
        user4.setMedicalLicenseNum("654321");
        user4.setSpecialization("Cardiologist");
        users.add(user4);

        User user5 = new User();
        user5.setId(5);
        user5.setFirstName("Ebuka");
        user5.setLastName("Ameafula");
        user5.setEmail("ebuka@gmail.com");
        user5.setPassword("Owlya123");
        user5.setDoctor(false);
        user5.setMedicalLicenseNum("");
        user5.setSpecialization("");
        users.add(user5);

        User user6 = new User();
        user6.setId(6);
        user6.setFirstName("Devin");
        user6.setLastName("Augot");
        user6.setEmail("daugot22@gmail.com");
        user6.setPassword("Keyin2021");
        user6.setDoctor(false);
        user6.setMedicalLicenseNum("");
        user6.setSpecialization("");
        users.add(user6);

        User user7 = new User();
        user7.setId(7);
        user7.setFirstName("Allison");
        user7.setLastName("Butler");
        user7.setEmail("abutler@gmail.com");
        user7.setPassword("Keyin2022");
        user7.setDoctor(false);
        user7.setMedicalLicenseNum("");
        user7.setSpecialization("");
        users.add(user7);

        User user8 = new User();
        user8.setId(8);
        user8.setFirstName("Lynn");
        user8.setLastName("Butler");
        user8.setEmail("LynnB@gmail.com");
        user8.setPassword("Keyin2023");
        user8.setDoctor(false);
        user8.setMedicalLicenseNum("");
        user8.setSpecialization("");
        users.add(user8);


        User user9 = new User();
        user9.setId(9);
        user9.setFirstName("Bob");
        user9.setLastName("Butler");
        user9.setEmail("Bob@gmail.com");
        user9.setPassword("password123");
        user9.setDoctor(false);
        user9.setMedicalLicenseNum("");
        user9.setSpecialization("");
        users.add(user9);

        // Insert all users into the database
        List<User> createdUsers = new ArrayList<>();
        for (User user : users) {
            try {
                boolean userCreated = UserDao.createUser(conn,user);
                if (userCreated) {
                    createdUsers.add(user);
                }
            } catch (SQLException e) {
                // Log the error message with stack trace
                System.err.println("Failed to create user: " + e.getMessage());
                e.printStackTrace();
            }
        }


        //  QUERY TO GET ALL USERS ON SYSTEM
        List<User> listOfUsers = new ArrayList<>();
        ResultSet resultSetForAllUsers = db.queryDatabase(conn, GET_ALL_USERS);
        int counter = 1;
        System.out.println("\n" + "*****{List of current users}*****");
        while (resultSetForAllUsers.next()) {
            User user = new User(resultSetForAllUsers.getInt("id"), resultSetForAllUsers.getString("first_name"),
                    resultSetForAllUsers.getString("last_name"), resultSetForAllUsers.getString("email"),
                    resultSetForAllUsers.getString("password"), resultSetForAllUsers.getBoolean("is_Doctor"),
                    resultSetForAllUsers.getString("medical_license_num"),
                    resultSetForAllUsers.getString("specialization"));
            listOfUsers.add(user);

            System.out.println(counter + ". " + user.getFirstName() + " " + user.getLastName());

            counter++;
        }

        // get the user at index 0 in the list and update their first name & last name
        User userToUpdate = users.get(1);
        userToUpdate.setFirstName("Ray");
        userToUpdate.setLastName("charles");

        // call the userDao to update the user in the database
        userDao.updateUser(conn, userToUpdate);

        // get the user at index 0 in the list and delete them
        User userToDelete = listOfUsers.get(0);
        int id = userToDelete.getId();
        userDao.deleteUser(conn, id);


        // calling getUserById method to get user by ID
        User user = userDao.getUserById(conn, 1);

        // printing the details of the retrieved user by ID search
        System.out.println("\n" + "User Search by ID:");
        System.out.println("ID: " + user.getId());
        System.out.println("First Name: " + user.getFirstName());
        System.out.println("Last Name: " + user.getLastName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Is Doctor: " + user.isDoctor());
        if (user.isDoctor()) {
            System.out.println("Medical License Number: " + user.getMedicalLicenseNum());
            System.out.println("Specialization: " + user.getSpecialization());
        }

        // get user by email
        User userByEmail = userDao.getUserByEmail(conn, "ebuka@gmail.com");

        // print user details for Email search
        System.out.println("\n" + "User Search by email:");
        System.out.println("ID: " + userByEmail.getId());
        System.out.println("First Name: " + userByEmail.getFirstName());
        System.out.println("Last Name: " + userByEmail.getLastName());
        System.out.println("Email: " + userByEmail.getEmail());
        System.out.println("Is Doctor: " + userByEmail.isDoctor());
        if (userByEmail.isDoctor()) {
            System.out.println("Medical License Number: " + userByEmail.getMedicalLicenseNum());
            System.out.println("Specialization: " + userByEmail.getSpecialization());
        }


        /* The menu system below is based on already existing users & They are dependent on if they are a patient
         *  or
         * doctor they will have access to particular portals (Look above for emails and passwords to test for use
         * cases but here's an example that's a patient Email : ebuka@gmail.com password = Owlya123 , here's a doctor
         * credentials that lead to the doctor portal Email: joker.smith@gmail.com Password: my_password122
         * ) */

        // Verify Password (User Login)
        DoctorPortalDao doctorPortalDao = new DoctorPortalDao(); // create an instance of DoctorPortalDao

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + "Enter your email: ");
        String email = scanner.nextLine();

        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        UserDao.LoginResult loginResult = userDao.verifyPassword(conn, email, password);
        while (loginResult == null) {
            System.out.println("Incorrect email or password. Please try again.");
            System.out.println("Enter your email: ");
            email = scanner.nextLine();
            System.out.println("Enter your password: ");
            password = scanner.nextLine();
            loginResult = userDao.verifyPassword(conn, email, password);
        }
            if (loginResult.isDoctor()) {
                boolean exit = false;
                while (!exit) {
                    System.out.println("\n" + "Welcome to the Doctor Portal! Please select an option:");
                    System.out.println("1. Get patient health data by patient ID:");
                    System.out.println("2. Get patients by doctor ID");
                    System.out.println("3. Get doctor by ID:");
                    System.out.println("4. Exit");
                    int option = scanner.nextInt();

                    switch (option) {
                        case 1:
                            System.out.println("Please enter the patient ID:");
                            int patientId = scanner.nextInt();
                            List<HealthData> healthDataList = doctorPortalDao.getHealthDataByPatientId(conn, patientId);
                            System.out.println("Health Data For That Patient:");
                            if (healthDataList.isEmpty()) {
                                System.out.println("No health data found for the patient.");
                            } else {
                                for (HealthData healthData : healthDataList) {
                                    System.out.println("Height in meters: " + healthData.getHeight());
                                    System.out.println("Weight in kg: " + healthData.getWeight());
                                    System.out.println("Steps: " + healthData.getSteps());
                                    System.out.println("Heart Rate: " + healthData.getHeartRate() + "bpm");
                                    System.out.println("Date Entered: " + healthData.getDate() + "\n");
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Please enter the doctor ID for their patients records:");
                            int doctorId2 = scanner.nextInt();
                            List<User> patients = doctorPortalDao.getPatientsByDoctorId(conn, doctorId2);
                            System.out.println("That Doctor's Patients are:");
                            for (User patient : patients) {
                                System.out.println(patient.getFirstName() + " " + patient.getLastName());
                            }
                            break;
                        case 3:
                            System.out.println("Please enter the doctor ID:");
                            int doctorId = scanner.nextInt();
                            Doctor doctor = doctorPortalDao.getDoctorById(conn, doctorId);
                            System.out.println("Doctor: " + doctor.getFirstName() + " " + doctor.getLastName() + "\n" + "Specialization: " + doctor.getSpecialization() + "\n" +
                                    "Medical License Number: " + doctor.getMedicalLicenseNumber() + "\n");
                            break;
                        case 4:
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid option selected.");
                            break;
                    }
                }
            } else {
                boolean exit = false;
                while (!exit) {
                    System.out.println("\n" + "Welcome to the Patient Portal! Please select an option:");
                    System.out.println("1. Create new health data");
                    System.out.println("2. Update your existing health data");
                    System.out.println("3. Delete your existing health data");
                    System.out.println("4. Generate recommendation's by patient ID");
                    System.out.println("5. Exit");
                    int option = scanner.nextInt();

                    switch (option) {
                        case 1:
                            // Prompt the user for input
                            Scanner scannerHealthData = new Scanner(System.in);
                            System.out.println("\nRegister your new user ID:");
                            int newId = scannerHealthData.nextInt();
                            System.out.println("\nEnter your new user ID:");
                            int userId = scannerHealthData.nextInt();
                            System.out.println("\nEnter your weight in kg:");
                            double weight = scannerHealthData.nextDouble();
                            System.out.println("Enter your height in meters:");
                            double height = scannerHealthData.nextDouble();
                            System.out.println("Enter your daily step count:");
                            int steps = scannerHealthData.nextInt();
                            System.out.println("Enter your heart rate:");
                            int heartRate = scannerHealthData.nextInt();
                            System.out.println("Enter the date (YYYY-MM-DD):");
                            String date = scannerHealthData.next();

                            // Insert the data into the health_data table
                            db.insertIntoHealthDataTable(conn,newId, userId, weight, height, steps, heartRate,
                                    Date.valueOf(date));

                            System.out.println("\nData inserted successfully!");
                            break;

                        case 2:
                            // Prompt the user for input
                            Scanner scannerHealthDataUpdate = new Scanner(System.in);
                            System.out.println("\nEnter the ID of the health data to update:");
                            int healthDataId = scannerHealthDataUpdate.nextInt();
                            System.out.println("\nEnter your user ID:");
                            userId = scanner.nextInt();
                            System.out.println("\nEnter the updated weight in kg:");
                            double updatedWeight = scannerHealthDataUpdate.nextDouble();
                            System.out.println("Enter the updated height in meters:");
                            double updatedHeight = scannerHealthDataUpdate.nextDouble();
                            System.out.println("Enter the updated daily step count:");
                            int updatedSteps = scannerHealthDataUpdate.nextInt();
                            System.out.println("Enter the updated heart rate:");
                            int updatedHeartRate = scannerHealthDataUpdate.nextInt();
                            System.out.println("Enter the date (YYYY-MM-DD):");
                            String newDate = scannerHealthDataUpdate.next();


                            // create a new HealthData object with the updated values
                            HealthData updatedHealthData = new HealthData(healthDataId, userId, updatedWeight,
                                    updatedHeight, updatedSteps, updatedHeartRate, Date.valueOf(newDate));
                            updatedHealthData.setId(userId);
                            updatedHealthData.setId(healthDataId);
                            updatedHealthData.setWeight(updatedWeight);
                            updatedHealthData.setHeight(updatedHeight);
                            updatedHealthData.setSteps(updatedSteps);
                            updatedHealthData.setHeartRate(updatedHeartRate);

                            // create a new instance of the HealthDataDAO class
                            HealthDataDao healthDataDAO = new HealthDataDao();

                            // call the updateHealthData method on the instance
                            boolean updateResult = healthDataDAO.updateHealthData(conn, updatedHealthData);

                            if (updateResult) {
                                System.out.println("\nData updated successfully!");
                            } else {
                                System.out.println("\nFailed to update data.");
                            }
                            break;
                        case 3:
                            // Prompt the user for input
                            Scanner scannerDeleteHealthData = new Scanner(System.in);
                            System.out.println("\nEnter the ID of patient's health data to delete:");
                            int healthDataId2 = scannerDeleteHealthData.nextInt();

                            // create a new instance of the HealthDataDAO class
                            HealthDataDao healthDataDao = new HealthDataDao();

                            // call the deleteHealthData method on the instance
                            boolean deleteResult = healthDataDao.deleteHealthData(conn, healthDataId2);

                            if (deleteResult) {
                                System.out.println("\nData deleted successfully!");
                            } else {
                                System.out.println("\nFailed to delete data.");
                            }
                            break;
                        case 4:
                            // Prompt the user for input
                            Scanner scannerGetHealthData = new Scanner(System.in);
                            System.out.println("\nEnter the ID of patient's health data to retrieve:");
                            int healthDataIdReco = scannerGetHealthData.nextInt();

                            // create a new instance of the HealthDataDao class
                            HealthDataDao healthDataDaoReco = new HealthDataDao();

                            // call the getHealthDataByUserId method on the instance
                            HealthData healthData = healthDataDaoReco.getHealthDataByUserId(conn, healthDataIdReco);

                            // check if health data was found for the user
                            if (healthData == null) {
                                System.out.println("\nNo health data found for that ID.");
                            } else {
                                // display the health data for the user for recommendation generation
                                System.out.println("\nHealth Data For Recommendation's:");
                                System.out.println("ID: " + healthData.getId());
                                System.out.println("User ID: " + healthData.getUserId());
                                System.out.println("Weight: " + healthData.getWeight() + " kg");
                                System.out.println("Height: " + healthData.getHeight() + " cm");
                                System.out.println("Steps: " + healthData.getSteps());
                                System.out.println("Heart Rate: " + healthData.getHeartRate() + " bpm");
                                System.out.println("Date: " + healthData.getDate());

                                // create a new instance of the RecommendationSystem class
                                RecommendationSystem recommendationSystem = new RecommendationSystem();

                                // call the getHealthRecommendations method on the instance to get health-specific recommendations
                                List<String> healthRecommendations = recommendationSystem.getHealthRecommendations(conn, healthDataIdReco);

                                // display the health-specific recommendations for the user
                                System.out.println("\nHealth-Specific Recommendations:");
                                for (String healthRecommendation : healthRecommendations) {
                                    System.out.println("- " + healthRecommendation);

                                    // insert recommendation into database
                                    int userId3 = healthData.getUserId();
                                    Date date2 = healthData.getDate();
                                    String recommendationText = String.join(" ", healthRecommendations);
                                    recommendationSystem.addRecommendations(conn, userId3, recommendationText, date2);
                                }

                            }
                            break;
                        case 5:
                            exit = true;
                            break;
                        default:
                            System.out.println("\nInvalid option. Please enter a valid option.");
                    }
                }

                // Creating a medicine reminder
                MedicineReminder medicineReminder1 = new MedicineReminder(9, 9, "Warafrin", "20MG",
                        Date.valueOf("2023-04-18"), Date.valueOf("2023-04-23"), Date.valueOf("2023-04-28"));

                MedicineReminderManager.createMedicineReminder(conn, medicineReminder1);

                Thread.sleep(1500);

                // Get reminders for a specific user by ID and users name
                MedicineReminderManager.getMedicineReminderByUserId(conn, 1);
                MedicineReminderManager.getMedicineReminderByUserId(conn, 9);
                MedicineReminderManager.getMedicineReminderByUserId(conn, 6);

                MedicineReminderManager.getMedicineReminderByUserName(conn, "Bob", "Butler");


            }
        }
    }