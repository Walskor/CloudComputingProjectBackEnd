package com.cloud.project.entity;
import lombok.Data;
import org.springframework.boot.*;
import jakarta.persistence.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    private String password;

    private boolean isGuide;

    // Getters and setters
}
