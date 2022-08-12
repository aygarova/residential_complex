package com.example.copmprob.model.dto;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ApartmentFreeTenantDtoTest {
    private ApartmentFreeTenantDto apartmentFreeTenantDto;

    @BeforeEach
    public void setup(){
        apartmentFreeTenantDto = new ApartmentFreeTenantDto(
                "A01F1",
                75.5,
                1,
                "TWO_ROOMS",
                "Occupied",
                "Pesho",
                "test@email.com",
                "0000000000"
        );
    }

    @Test
    public void testEmptyConstructor(){
        ApartmentFreeTenantDto apartmentEnterDto1
                = new ApartmentFreeTenantDto();
    }


    @Test
    public void testGetAndMethodsApartmentNumber(){
        apartmentFreeTenantDto.setApartmentNumber("A01F1");
        Assertions.assertEquals("A01F1",apartmentFreeTenantDto.getApartmentNumber());
    }

    @Test
    public void testGetAndMethodsArea(){
        apartmentFreeTenantDto.setArea(75.5);
        Assertions.assertEquals(75.5,apartmentFreeTenantDto.getArea());
    }

    @Test
    public void testGetAndMethodsFloor(){
        apartmentFreeTenantDto.setFloor(1);
        Assertions.assertEquals(1,apartmentFreeTenantDto.getFloor());
    }

    @Test
    public void testGetAndMethodsApartmentType(){
        apartmentFreeTenantDto.setApartmentType("TWO_ROOMS");
        Assertions.assertEquals("TWO_ROOMS",apartmentFreeTenantDto.getApartmentType());
    }


    @Test
    public void testGetAndMethodsStatusEnum(){
        apartmentFreeTenantDto.setStatus("Occupied");
        Assertions.assertEquals("Occupied",apartmentFreeTenantDto.getStatus());
    }

    @Test
    public void testGetAndMethodsPhoneNumber(){
        apartmentFreeTenantDto.setOwnerPhone("0000000000");
        Assertions.assertEquals("0000000000",apartmentFreeTenantDto.getOwnerPhone());
    }

    @Test
    public void testGetAndMethodsEmail(){
        apartmentFreeTenantDto.setOwnerEmail("test@email.com");
        Assertions.assertEquals("test@email.com",apartmentFreeTenantDto.getOwnerEmail());
    }

    @Test
    public void testGetAndMethodsOwnerName(){
        apartmentFreeTenantDto.setOwnerName("Pesho");
        Assertions.assertEquals("Pesho",apartmentFreeTenantDto.getOwnerName());
    }
}
