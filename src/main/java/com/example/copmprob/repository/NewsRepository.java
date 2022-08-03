package com.example.copmprob.repository;

import com.example.copmprob.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News,Long> {
    List<News> findAllByName(String name);
}
