package com.keyin.model;
import java.sql.Date;

public class MedicineReminder {
    private int id;
    private int userId;
    private String medicineName;
    private String dosage;
    private Date schedule;
    private Date startDate;
    private Date endDate;


    public MedicineReminder(int id, int userId, String medicineName, String dosage, Date schedule,
                            Date startDate, Date endDate) {
        this.id = id;
        this.userId = userId;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.schedule = schedule;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }



    @Override
    public String toString() {
        return "MedicineReminder{" +
                "id=" + id +
                ", userId=" + userId +
                ", medicineName='" + medicineName + '\'' +
                ", dosage='" + dosage + '\'' +
                ", schedule=" + schedule +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

}
