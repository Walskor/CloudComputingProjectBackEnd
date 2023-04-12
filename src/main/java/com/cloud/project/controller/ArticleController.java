package com.cloud.project.controller;


import com.cloud.project.common.ErrorCode;
import com.cloud.project.common.ResponseEntity;
import com.cloud.project.entity.Article;
import com.cloud.project.entity.User;
import com.cloud.project.service.ArticleService;
import com.cloud.project.service.UserService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Integer id) {
        Article article = articleService.findById(id);
        if (article == null){
            return ResponseEntity.error(ErrorCode.TargetNotExists,"Id invalid",null);
        }
        return ResponseEntity.resultBuild(1, article);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<Article>> getArticleByAuthor(@PathVariable Integer id) {
        List<Article> articles = articleService.findByAuthor(id);
        if (articles == null){
            return ResponseEntity.error(ErrorCode.TargetNotExists,"No article",null);
        }
        return ResponseEntity.pageBuild(articles.size(), articles);
    }
    @GetMapping("/title/{title}")
    public ResponseEntity<List<Article>> getArticleByTitle(@PathVariable String title) {
        List<Article> articles = articleService.findByTitle(title);
        if (articles == null){
            return ResponseEntity.error(ErrorCode.TargetNotExists,"No related articles",null);
        }
        return ResponseEntity.pageBuild(articles.size(), articles);
    }

    @PostMapping("/create")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        int affectedRows = articleService.createArticle(article);
        if (affectedRows == 1){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.error(ErrorCode.RegisterFailed, "Create Article Failed!",null);


    }
}

