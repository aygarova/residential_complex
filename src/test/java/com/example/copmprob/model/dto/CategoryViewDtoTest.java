package com.example.copmprob.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryViewDtoTest {
    private CategoryDto category;

    @BeforeEach
    public void setup(){
        this.category =  new CategoryDto("Message");
    }

    @Test
    public void testEmptyConstructor(){
        CategoryDto category1 = new CategoryDto();
    }

    @Test
    public void testGetAndMethodsCategoryName(){
        category.setCategoryName("Message");
        Assertions.assertEquals("Message",category.getCategoryName());
    }
}
