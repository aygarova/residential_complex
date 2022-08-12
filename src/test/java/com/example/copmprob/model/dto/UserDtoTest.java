package com.example.copmprob.model.dto;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserDtoTest {
    private UserDto user;

    @BeforeEach
    public void setup(){
        this.user = new UserDto(
                1,
                "Pesho",
                "Petrov",
                "Pesho1",
                "123456",
                "123456",
                "0000000000",
                "test@email.com",
                "OWNER"
        );
    }

    @Test
    public void testEmptyConstructor(){
        UserDto users = new UserDto();
    }

    @Test
    public void testConstructorWithUsernameAndPassword(){
        UserDto users = new UserDto(
                "Pesho1",
                "123456");
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
    public void testGetAndMethodsPassword(){
        user.setPassword("123456");
        Assertions.assertEquals("123456",user.getPassword());
    }

    @Test
    public void testGetAndMethodsConfirmPassword(){
        user.setConfirmPassword("123456");
        Assertions.assertEquals("123456",user.getConfirmPassword());
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
    public void testGetAndMethodsRole(){
        user.setRole("TENANT");
        Assertions.assertEquals("TENANT",user.getRole());
    }
}
