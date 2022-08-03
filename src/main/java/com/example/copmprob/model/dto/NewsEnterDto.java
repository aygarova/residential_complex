package com.example.copmprob.model.dto;

import com.example.copmprob.model.entity.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class NewsEnterDto {
    @NotBlank(message = "Name can't be empty")
    private String name;

    @NotBlank(message = "Name can't be empty")
    @Size(min = 10, message = "Description must be more than 10 elements")
    private String descriptions;


    private String category;
}
