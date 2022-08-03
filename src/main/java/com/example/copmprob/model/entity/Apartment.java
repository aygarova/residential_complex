package com.example.copmprob.model.entity;

import com.example.copmprob.model.enums.ApartmentType;
import com.example.copmprob.model.enums.StatusEnum;

import javax.persistence.*;

@Entity
@Table(name = "apartments")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "apartment_number", nullable = false, unique = true)
    private String apartmentNumber;

    @Column(name = "area",nullable = false)
    private double area;

    @Column(name = "floor",nullable = false)
    private int floor;

    @Column(name = "apartment_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private ApartmentType apartmentType;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne
    private Users owner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tenant_id", referencedColumnName = "id")
    private Users tenant;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Users getTenant() {
        return tenant;
    }

    public void setTenant(Users tenant) {
        this.tenant = tenant;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

}
