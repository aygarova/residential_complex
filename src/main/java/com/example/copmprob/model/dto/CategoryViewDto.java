package com.example.copmprob.model.dto;

public class CategoryViewDto {
    private String categoryName;

    public CategoryViewDto() {
    }

    public CategoryViewDto(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
