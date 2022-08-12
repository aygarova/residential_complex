package com.example.copmprob.service;

import com.example.copmprob.model.dto.CategoryDto;
import com.example.copmprob.model.entity.Category;
import com.example.copmprob.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

public class CategoryServiceTest {
    private CategoryRepository mockedCategoryRepository;
    private  ModelMapper modelMapper = new ModelMapper();
    private Category category;
    private CategoryDto categoryDto;
    private CategoryService categoryService;

    @BeforeEach
    public void setup(){
        category = new Category("Message");
        categoryDto = new CategoryDto("Message");
        mockedCategoryRepository = Mockito.mock(CategoryRepository.class);
        categoryService = new CategoryService(mockedCategoryRepository,modelMapper);
    }

    @Test
    public void testFindAllACategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        Mockito.when(mockedCategoryRepository.findAll()).thenReturn(categories);
        Assertions.assertEquals(categories,categoryService.findAllACategories());
    }


    @Test
    public void testHaveNotThisCategory() {
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        Mockito.when(mockedCategoryRepository.findByCategoryName("name")).thenReturn(categories);
        Assertions.assertFalse(categoryService.haveThisCategory("name"));
    }

    @Test
    public void testHaveThisCategory() {
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        Mockito.when(mockedCategoryRepository.findByCategoryName("Message")).thenReturn(categories);
        Assertions.assertTrue(categoryService.haveThisCategory("Message"));
    }
}
