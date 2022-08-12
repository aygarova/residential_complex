package com.example.copmprob.model.entity;

import com.example.copmprob.model.enums.ApartmentType;
import com.example.copmprob.model.enums.RoleEnum;
import com.example.copmprob.model.enums.StatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class UsersTest {
    private Users user;

    @BeforeEach
    public void setup(){
        this.user = new Users(
                "Pesho1",
                "Pesho",
                "Petrov",
                "123456",
                "0000000000",
                "test@email.com",
                RoleEnum.OWNER
        );
    }

    @Test
    public void testEmptyConstructor(){
        Users users = new Users();
    }

    @Test
    public void testGetAndMethodsId(){
        user.setId(1);
        Assertions.assertEquals(1,user.getId());
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
    public void testGetAndMethodsApartments(){
        Apartment apartment = new Apartment(
                1,
                "A01F1",
                75.5,
                1,
                ApartmentType.TWO_ROOMS,
                StatusEnum.Occupied
        );
        Set<Apartment> apartments = new HashSet<>();
        apartments.add(apartment);
        user.setApartments(apartments);
        Assertions.assertEquals(apartments,user.getApartments());
    }

    @Test
    public void testGetAndMethodsRole(){
        user.setRole(RoleEnum.TENANT);
        Assertions.assertEquals(RoleEnum.TENANT,user.getRole());
    }

    @Test
    public void testGetAndMethodsNews(){
        Category category = new Category("Message");
        News news = new News(
                1,
                "NameNews",
                "Test news description",
                LocalDate.now(),
                category,
                user

        );
        Set<News> newsSet = new HashSet<>();
        newsSet.add(news);
        user.setNews(newsSet);
        Assertions.assertEquals(newsSet,user.getNews());
    }

    @Test
    public void testGetAndMethodsRentApartments(){
        Apartment apartment =  new Apartment(
                1,
                "A01F1",
                75.5,
                1,
                ApartmentType.TWO_ROOMS,
                StatusEnum.Occupied
        );
        user.setRentApartments(apartment);
        Assertions.assertEquals(apartment,user.getRentApartments());
    }
}
