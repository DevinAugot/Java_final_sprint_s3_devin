package com.keyin.model;

public class User {

    private String medicalLicenseNum;
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isDoctor;

    private String specialization;

    public User(int id, String firstName, String lastName, String email, String password, boolean isDoctor,
                String medicalLicenseNum,String specialization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isDoctor = isDoctor;
        this.medicalLicenseNum = medicalLicenseNum;
        this.specialization = specialization;
    }

    public User() {

    }


    public String getMedicalLicenseNum() {
        return medicalLicenseNum;
    }

    public void setMedicalLicenseNum(String medicalLicenseNum) {
        this.medicalLicenseNum = medicalLicenseNum;
    }
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isDoctor=" + isDoctor +
                '}';
    }


}
