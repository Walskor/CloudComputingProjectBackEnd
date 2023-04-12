package com.cloud.project.entity;

import lombok.Data;

@Data
public class Message {
    private int id;
    private int sender;
    private String message;
}
