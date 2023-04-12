package com.cloud.project.service.impl;

import com.cloud.project.dao.ArticleDao;
import com.cloud.project.dao.UserDao;
import com.cloud.project.entity.Article;
import com.cloud.project.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;
    @Override
    public Article findById(int id) {
        return articleDao.findById(id);
    }

    @Override
    public List<Article> findByTitle(String title) {
        List<Article> articles = articleDao.findByTitle(title);
        if (articles.size() == 0){
            return null;
        }
        return articles;
    }

    @Override
    public List<Article> findByAuthor(int authorId) {
        List<Article> articles = articleDao.findByAuthor(authorId);
        if (articles.size() == 0){
            return null;
        }
        return articles;
    }

    @Override
    public int createArticle(Article article) {
        return articleDao.createArticle(article);
    }
}
