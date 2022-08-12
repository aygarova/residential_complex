package com.example.copmprob.model.entity;

import com.example.copmprob.model.enums.RoleEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CategoryTest {
    private Category category;

    @BeforeEach
    public void setup(){
        this.category =  new Category("Message");
    }

    @Test
    public void testEmptyConstructor(){
        Category category1 = new Category();
    }

    @Test
    public void testGetAndMethodsId(){
        category.setId(1);
        Assertions.assertEquals(1,category.getId());
    }

    @Test
    public void testGetAndMethodsCategoryName(){
        category.setCategoryName("Message");
        Assertions.assertEquals("Message",category.getCategoryName());
    }

    @Test
    public void testGetAndMethodsNews(){
        Users user = new Users(
                "Pesho1",
                "Pesho",
                "Petrov",
                "123456",
                "0000000000",
                "test@email.com",
                RoleEnum.OWNER
        );
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
        category.setNews(newsSet);
        Assertions.assertEquals(news,category.getNews());
    }

}
