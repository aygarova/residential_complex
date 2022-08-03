package com.example.copmprob.cotroller;

import com.example.copmprob.model.dto.*;
import com.example.copmprob.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String allCategories(Model model){
        if (categoryService.findAllACategories().isEmpty()){
           return "add-category";
        }
        model.addAttribute("categories",categoryService.findAllACategories());

        return "categories";
    }

    @GetMapping("/category-enter")
    public String categoryEnter(){
        return "add-category";
    }

    @PostMapping("/category-enter")
    public String addCategory(@Valid CategoryDto categoryDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("categoryDto", categoryDto);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.categoryDto"
                            , bindingResult);

            return "add-category";
        }

        categoryService.addThisCategory(categoryDto);
        return "admin";
    }

    @ModelAttribute
    public CategoryDto categoryDto(){
        return new CategoryDto();
    }


}
