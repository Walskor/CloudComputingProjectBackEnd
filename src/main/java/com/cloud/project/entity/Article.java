package com.cloud.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int authorId;

    private String title;

    private String content;

    private double price;

    private int count;

    // Getters and setters
}