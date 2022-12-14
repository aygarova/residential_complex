package com.example.copmprob.model.dto;

import java.util.List;

public class UserWithApartmentDto {
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private String email;
    private List<String> apartmentsNumber;

    public UserWithApartmentDto() {
    }

    public UserWithApartmentDto(String firstName, String lastName, String username, String phoneNumber, String email, List<String> apartmentsNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.apartmentsNumber = apartmentsNumber;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getApartmentsNumber() {
        return apartmentsNumber;
    }

    public void setApartmentsNumber(List<String> apartmentsNumber) {
        this.apartmentsNumber = apartmentsNumber;
    }
}
