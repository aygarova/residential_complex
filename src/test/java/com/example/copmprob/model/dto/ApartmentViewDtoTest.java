package com.example.copmprob.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ApartmentViewDtoTest {
    private ApartmentViewDto apartmentViewDto;

    @BeforeEach
    public void setup(){
        apartmentViewDto = new ApartmentViewDto(
                "A01F1",
                75.5,
                1,
                "TWO_ROOMS",
                "Occupied",
                "Pesho"
        );
    }

    @Test
    public void testEmptyConstructor(){
        ApartmentViewDto apartmentEnterDto1
                = new ApartmentViewDto();
    }


    @Test
    public void testGetAndMethodsApartmentNumber(){
        apartmentViewDto.setApartmentNumber("A01F1");
        Assertions.assertEquals("A01F1",apartmentViewDto.getApartmentNumber());
    }

    @Test
    public void testGetAndMethodsArea(){
        apartmentViewDto.setArea(75.5);
        Assertions.assertEquals(75.5,apartmentViewDto.getArea());
    }

    @Test
    public void testGetAndMethodsFloor(){
        apartmentViewDto.setFloor(1);
        Assertions.assertEquals(1,apartmentViewDto.getFloor());
    }

    @Test
    public void testGetAndMethodsApartmentType(){
        apartmentViewDto.setApartmentType("TWO_ROOMS");
        Assertions.assertEquals("TWO_ROOMS",apartmentViewDto.getApartmentType());
    }

    @Test
    public void testGetAndMethodsStatusEnum(){
        apartmentViewDto.setStatus("Occupied");
        Assertions.assertEquals("Occupied",apartmentViewDto.getStatus());
    }

    @Test
    public void testGetAndMethodsOwnerName(){
        apartmentViewDto.setTenant("Pesho");
        Assertions.assertEquals("Pesho",apartmentViewDto.getTenant());
    }

}
