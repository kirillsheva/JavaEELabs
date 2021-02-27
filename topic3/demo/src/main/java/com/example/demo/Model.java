package com.example.demo;

import lombok.Data;

import javax.persistence.Id;

@Data
class Model {
    @Id
    private String isbn;
    private String title;
    private String author;
}