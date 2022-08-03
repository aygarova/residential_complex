package com.example.copmprob.service;

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

    public void addThisNews(NewsAddDto newsAddDto) {
        List<News> allNews = newsRepository.findAll();
        if (allNews.isEmpty()){
            saveThisNews(newsAddDto);
        }
        if (!haveThisNews(newsAddDto.getName(), newsAddDto.getDescriptions())){
            saveThisNews(newsAddDto);
        }
        //todo exception
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
        } // todo exception
        news.setAuthor(apartmentService.findCurrentUser());
        newsRepository.save(news);
    }

}
