package com.example.copmprob.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewsAddDto {
    @NotBlank(message = "News name can't be empty")
    @Size(min = 3, max = 30, message = "News name must be between 3 and 30 elements")
    private String name;

    @NotBlank(message = "News name can't be empty")
    @Size(min = 10, message = "Descriptions must be more than 10 elements")
    private String descriptions;

    @NotBlank(message = "Category can't be empty")
    private String category;

    public NewsAddDto() {
    }

    public NewsAddDto(String name, String descriptions, String category) {
        this.name = name;
        this.descriptions = descriptions;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
