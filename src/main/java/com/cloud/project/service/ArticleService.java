package com.cloud.project.service;

import com.cloud.project.entity.Article;

import java.util.List;

public interface ArticleService {
    Article findById(int id);
    List<Article> findByTitle(String title);
    List<Article> findByAuthor(int authorId);
    int createArticle(Article article);
}
