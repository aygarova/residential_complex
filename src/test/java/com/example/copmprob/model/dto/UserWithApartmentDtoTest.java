package com.example.copmprob.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import java.util.List;


public class UserWithApartmentDtoTest {
    private UserWithApartmentDto user;
    @BeforeEach
    public void setup(){
        List<String> apartmentsNumber = new ArrayList<>();
        apartmentsNumber.add("A01F1");
        this.user = new UserWithApartmentDto(
                "Pesho",
                "Petrov",
                "Pesho1",
                "0000000000",
                "test@email.com",
                apartmentsNumber
        );
    }

    @Test
    public void testEmptyConstructor(){
        UserWithApartmentDto user = new UserWithApartmentDto();
    }

    @Test
    public void testGetAndMethodsUsername(){
        user.setUsername("Pesho1");
        Assertions.assertEquals("Pesho1",user.getUsername());
    }

    @Test
    public void testGetAndMethodsFirstName(){
        user.setFirstName("Pesho");
        Assertions.assertEquals("Pesho",user.getFirstName());
    }

    @Test
    public void testGetAndMethodsLastName(){
        user.setLastName("Petrov");
        Assertions.assertEquals("Petrov",user.getLastName());
    }


    @Test
    public void testGetAndMethodsPhoneNumber(){
        user.setPhoneNumber("0000000000");
        Assertions.assertEquals("0000000000",user.getPhoneNumber());
    }

    @Test
    public void testGetAndMethodsEmail(){
        user.setEmail("test@email.com");
        Assertions.assertEquals("test@email.com",user.getEmail());
    }

    @Test
    public void testGetAndMethodsApartmentsNumber(){
        List<String> apartmentsNumber = new ArrayList<>();
        apartmentsNumber.add("A01F1");
        user.setApartmentsNumber(apartmentsNumber);
        Assertions.assertEquals(apartmentsNumber,user.getApartmentsNumber());
    }
}
