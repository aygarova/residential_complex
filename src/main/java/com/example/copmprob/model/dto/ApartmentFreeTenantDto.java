package com.example.copmprob.model.dto;

public class ApartmentFreeTenantDto {
    private String apartmentNumber;

    private double area;

    private int floor;

    private String apartmentType;

    private String status;

    private String ownerName;

    private String ownerEmail;

    private String ownerPhone;

    public ApartmentFreeTenantDto() {
    }

    public ApartmentFreeTenantDto(String apartmentNumber, double area, int floor, String apartmentType, String status, String ownerName, String ownerEmail, String ownerPhone) {
        this.apartmentNumber = apartmentNumber;
        this.area = area;
        this.floor = floor;
        this.apartmentType = apartmentType;
        this.status = status;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
        this.ownerPhone = ownerPhone;
    }

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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }
}
