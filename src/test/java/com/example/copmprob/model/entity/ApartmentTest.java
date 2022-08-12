package com.example.copmprob.model.entity;

import com.example.copmprob.model.enums.ApartmentType;
import com.example.copmprob.model.enums.StatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ApartmentTest {
    private Apartment apartment;

    @BeforeEach
    public void setup(){
        this.apartment = new Apartment(
                1,
               "A01F1",
                75.5,
                1,
                ApartmentType.TWO_ROOMS,
                StatusEnum.Occupied
        );
    }

    @Test
    public void testEmptyConstructor(){
        Apartment apartments = new Apartment();
    }

    @Test
    public void testGetAndMethodsId(){
        apartment.setId(1);
        Assertions.assertEquals(1,apartment.getId());
    }

    @Test
    public void testGetAndMethodsApartmentNumber(){
        apartment.setApartmentNumber("A01F1");
        Assertions.assertEquals("A01F1",apartment.getApartmentNumber());
    }

    @Test
    public void testGetAndMethodsArea(){
        apartment.setArea(75.5);
        Assertions.assertEquals(75.5,apartment.getArea());
    }

    @Test
    public void testGetAndMethodsFloor(){
        apartment.setFloor(1);
        Assertions.assertEquals(1,apartment.getFloor());
    }

    @Test
    public void testGetAndMethodsApartmentType(){
        apartment.setApartmentType(ApartmentType.TWO_ROOMS);
        Assertions.assertEquals(ApartmentType.TWO_ROOMS,apartment.getApartmentType());
    }


    @Test
    public void testGetAndMethodsStatusEnum(){
        apartment.setStatus(StatusEnum.Occupied);
        Assertions.assertEquals(StatusEnum.Occupied,apartment.getStatus());
    }

    @Test
    public void testGetAndMethodsUsersOwner(){
        Users users = new Users();
        apartment.setOwner(users);
        Assertions.assertEquals(users,apartment.getOwner());
    }

    @Test
    public void testGetAndMethodsUsersTenant(){
        Users users = new Users();
        apartment.setTenant(users);
        Assertions.assertEquals(users,apartment.getTenant());
    }

}
