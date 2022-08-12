package com.example.copmprob.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class NewsAddDtoTest {
    private NewsAddDto news;
    @BeforeEach
    public void setup(){
        this.news =  new NewsAddDto(
                "NameNews",
                "Test news description",
                "Message"
        );
    }

    @Test
    public void testEmptyConstructor(){
        NewsAddDto news1 = new NewsAddDto();
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
    public void testGetAndMethodsCategory(){
        news.setCategory("Message");
        Assertions.assertEquals("Message",news.getCategory());
    }

}
