package com.example.demo;

import lombok.Data;

import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Model {
    @Id
    private String isbn;
    private String title;
    private String author;
}
