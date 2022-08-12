package com.example.copmprob.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class ApartmentEnterDtoTest {
    private ApartmentEnterDto apartmentEnterDto;

    @BeforeEach
    public void setup(){
        apartmentEnterDto = new ApartmentEnterDto("A01F1");
    }

    @Test
    public void testEmptyConstructor(){
        ApartmentEnterDto apartmentEnterDto1
                = new ApartmentEnterDto();
    }


    @Test
    public void testGetAndMethodsId(){
        apartmentEnterDto.setApartmentNumber("A01F1");
        Assertions.assertEquals("A01F1",apartmentEnterDto.getApartmentNumber());
    }
}
