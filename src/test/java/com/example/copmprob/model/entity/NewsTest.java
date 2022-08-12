package com.example.copmprob.model.entity;

import com.example.copmprob.model.enums.RoleEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class NewsTest {
    private News news;
    private Category category;
    private Users user;

    @BeforeEach
    public void setup(){
        category = new Category("Message");
        user = new Users(
                "Pesho1",
                "Pesho",
                "Petrov",
                "123456",
                "0000000000",
                "test@email.com",
                RoleEnum.OWNER
        );
        this.news =  new News(
                1,
                "NameNews",
                "Test news description",
                LocalDate.now(),
                category,
                user

        );
    }

    @Test
    public void testEmptyConstructor(){
        News news1 = new News();
    }

    @Test
    public void testGetAndMethodsId(){
        news.setId(1);
        Assertions.assertEquals(1,news.getId());
    }

    @Test
    public void testGetAndMethodsName(){
        news.setName("NameNews");
        Assertions.assertEquals("NameNews",news.getName());
    }

    @Test
    public void testGetAndMethodsDescriptions(){
        news.setDescriptions("Test news description");
        Assertions.assertEquals("Test news description",news.getDescriptions());
    }

    @Test
    public void testGetAndMethodsDateFrom(){
        news.setDateFrom(LocalDate.now());
        Assertions.assertEquals(LocalDate.now(),news.getDateFrom());
    }

    @Test
    public void testGetAndMethodsCategory(){
        news.setCategory(category);
        Assertions.assertEquals(category,news.getCategory());
    }

    @Test
    public void testGetAndMethodsAuthor(){
        news.setAuthor(user);
        Assertions.assertEquals(user,news.getAuthor());
    }
}
