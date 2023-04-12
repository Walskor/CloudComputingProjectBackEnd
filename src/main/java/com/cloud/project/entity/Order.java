package com.cloud.project.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int articleId;

    private int buyerId;

    private Timestamp timeStamp;

    private int count;
}
