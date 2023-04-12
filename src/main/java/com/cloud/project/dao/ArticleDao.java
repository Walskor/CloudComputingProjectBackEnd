package com.cloud.project.dao;

import com.cloud.project.entity.Article;
import com.cloud.project.entity.User;

import java.util.List;

public interface ArticleDao {
    Article findById(int id);
    List<Article> findByTitle(String title);
    List<Article> findByAuthor(int authorId);
    int createArticle(Article article);
    int addCount(int id);
}
