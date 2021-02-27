package com.example.demo;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
class Book {
    @Id
    private String isbn;
    private String title;
    private String author;
}