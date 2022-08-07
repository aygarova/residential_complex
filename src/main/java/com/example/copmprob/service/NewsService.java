package com.example.copmprob.service;

import com.example.copmprob.exceptions.ExceptionMessages;
import com.example.copmprob.exceptions.WrongActionException;
import com.example.copmprob.model.dto.NewsAddDto;
import com.example.copmprob.model.entity.Category;
import com.example.copmprob.model.entity.News;
import com.example.copmprob.repository.CategoryRepository;
import com.example.copmprob.repository.NewsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final ApartmentService apartmentService;
    private final ModelMapper modelMapper;

    public NewsService(NewsRepository newsRepository, CategoryRepository categoryRepository, CategoryService categoryService, ApartmentService apartmentService, ModelMapper modelMapper) {
        this.newsRepository = newsRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
        this.apartmentService = apartmentService;
        this.modelMapper = modelMapper;
    }

    public List<News> findAllNews() {
        return newsRepository.findAll();
    }

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    public void addThisNews(NewsAddDto newsAddDto) {
        if (!haveThisNews(newsAddDto.getName(), newsAddDto.getDescriptions())){
           if (categoryService.haveThisCategory(newsAddDto.getCategory())){
               saveThisNews(newsAddDto);
           }else {
               throw new WrongActionException(ExceptionMessages.CATEGORY_NOT_FOUND_EXCEPTION);
           }
        }else {
            throw new WrongActionException(ExceptionMessages.NEWS_ALREADY_EXIST_EXCEPTIONS);
        }
    }

    private boolean haveThisNews(String name, String descriptions) {
        List<News> allNews = newsRepository.findAllByName(name);
        for (News news:allNews) {
            if (news.getName().equals(name) && news.getDescriptions().equals(descriptions)){
                return true;
            }
        }
        return false;
    }

    private void saveThisNews(NewsAddDto newsAddDto) {
        News news = modelMapper.map(newsAddDto, News.class);
        if (categoryService.haveThisCategory(newsAddDto.getCategory())){
            Category category = categoryRepository.findCategoriesByCategoryName(newsAddDto.getCategory());
            news.setCategory(category);
        } else {
            throw new WrongActionException(ExceptionMessages.CATEGORY_NOT_FOUND_EXCEPTION);
        }
        news.setAuthor(apartmentService.findCurrentUser());
        newsRepository.save(news);
    }

}
