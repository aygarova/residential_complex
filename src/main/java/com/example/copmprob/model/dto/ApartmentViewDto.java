package com.example.copmprob.model.dto;

import com.example.copmprob.model.entity.Users;
import com.example.copmprob.model.enums.ApartmentType;
import com.example.copmprob.model.enums.StatusEnum;

public class ApartmentViewDto {
    private String apartmentNumber;

    private double area;

    private int floor;

    private String apartmentType;

    private String status;

    private String  tenant;

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
}
