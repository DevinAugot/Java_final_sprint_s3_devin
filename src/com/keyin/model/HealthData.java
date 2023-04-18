package com.keyin.model;
import java.sql.Date;

public class HealthData {
    private int id;
    private int userId;
    private double weight;
    private double height;
    private int steps;
    private int heartRate;
    private Date date;

    public HealthData(int id, int userId, double weight, double height, int steps, int heartRate, Date date) {
        this.id = id;
        this.userId = userId;
        this.weight = weight;
        this.height = height;
        this.steps = steps;
        this.heartRate = heartRate;
        this.date = date;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "HealthData{" +
                "id=" + id +
                ", userId=" + userId +
                ", weight=" + weight +
                ", height=" + height +
                ", steps=" + steps +
                ", heartRate=" + heartRate +
                ", date='" + date + '\'' +
                '}';
    }


}
