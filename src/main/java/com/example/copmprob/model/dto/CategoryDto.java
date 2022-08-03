package com.example.copmprob.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoryDto {
    @NotBlank(message = "Category name can't be empty")
    private String categoryName;

    public CategoryDto() {
    }

    public CategoryDto(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
