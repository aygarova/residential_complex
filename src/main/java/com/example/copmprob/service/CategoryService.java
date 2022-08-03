package com.example.copmprob.service;

import com.example.copmprob.model.dto.CategoryDto;
import com.example.copmprob.model.entity.Category;
import com.example.copmprob.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public List<Category> findAllACategories() {
        return categoryRepository.findAll();
    }

    public void addThisCategory(CategoryDto categoryDto) {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()){
            categoryRepository.save(modelMapper.map(categoryDto,Category.class));
        }
            if (!haveThisCategory(categoryDto.getCategoryName())){
                categoryRepository.save(modelMapper.map(categoryDto,Category.class));
            }
            //todo exception
            System.out.printf("Have this category!");
    }

    public boolean haveThisCategory(String name){
        List<Category> categories = categoryRepository.findByCategoryName(name);
        for (Category c:categories) {
            if (c.getCategoryName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
