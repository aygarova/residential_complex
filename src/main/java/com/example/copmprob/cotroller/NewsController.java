package com.example.copmprob.cotroller;

import com.example.copmprob.model.dto.CategoryDto;
import com.example.copmprob.model.dto.NewsAddDto;
import com.example.copmprob.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public String allNews(Model model){
        if (newsService.findAllNews().isEmpty()){
            return "add-news";
        }
        model.addAttribute("news",newsService.findAllNews());

        return "news";
    }

    @GetMapping("add-news")
    public String newsEnter(Model model){
        model.addAttribute("category",newsService.findAllCategory());

        return "add-news";
    }

    @PostMapping("/news")
    public String addNews(@Valid NewsAddDto newsAddDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("newsAddDto", newsAddDto);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.newsAddDto"
                            , bindingResult);

            return "add-news";
        }

        newsService.addThisNews(newsAddDto);
        return "home";
    }

    @ModelAttribute
    public NewsAddDto newsAddDto(){
        return new NewsAddDto();
    }
    @ModelAttribute
    public CategoryDto categoryDto(){
        return new CategoryDto();
    }
}
