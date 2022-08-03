package com.example.copmprob.model.dto;

import javax.validation.constraints.NotBlank;

public class ApartmentEnterDto {

    @NotBlank(message = "Apartment number can't be empty")
    private String apartmentNumber;

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }
}
