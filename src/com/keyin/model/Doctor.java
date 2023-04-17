package com.keyin.model;

public class Doctor extends User{

    private String medicalLicenseNumber;
    private String specialization;
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isDoctor;

    public Doctor() {
    }
    public String getMedicalLicenseNumber() {
        return medicalLicenseNumber;
    }

    public void setMedicalLicenseNumber(String medicalLicenseNumber) {
        this.medicalLicenseNumber = medicalLicenseNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isDoctor() {
        return isDoctor;
    }

    @Override
    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }




    @Override
    public String toString() {
        return "Doctor{" +
                "medicalLicenseNumber='" + medicalLicenseNumber + '\'' +
                ", specialization='" + specialization + '\'' +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isDoctor=" + isDoctor +
                '}';
    }

}

