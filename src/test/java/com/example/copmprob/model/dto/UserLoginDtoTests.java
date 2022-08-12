package com.example.copmprob.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserLoginDtoTests {
    private UserLoginDto user;

    @BeforeEach
    public void setup(){
        this.user = new UserLoginDto(
                "Pesho1",
                "123456"
        );
    }

    @Test
    public void testEmptyConstructor(){
        UserLoginDto users = new UserLoginDto();
    }



    @Test
    public void testGetAndMethodsUsername(){
        user.setUsername("Pesho1");
        Assertions.assertEquals("Pesho1",user.getUsername());
    }


    @Test
    public void testGetAndMethodsPassword(){
        user.setPassword("123456");
        Assertions.assertEquals("123456",user.getPassword());
    }

  

}
