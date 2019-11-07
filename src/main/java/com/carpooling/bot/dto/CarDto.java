package com.carpooling.bot.dto;

import com.carpooling.bot.domain.CpCar;

public class CarDto {
    private Integer car_id;
    private String brand;
    private String model;
    private String enrollmentNumber;
    private int capacity;

    public CarDto(){

    }

    public CarDto(CpCar cpCar) {
        this.car_id = cpCar.getCarId();
        this.brand = cpCar.getBrand();
        this.model = cpCar.getModel();
        this.enrollmentNumber = cpCar.getEnrollmentNumber();
        this.capacity = cpCar.getCapacity();
    }

    public Integer getCar_id() {
        return car_id;
    }

    public void setCar_id(Integer car_id) {
        this.car_id = car_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
