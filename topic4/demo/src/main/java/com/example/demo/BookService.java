package com.example.demo;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BooksRepository repository;
    public BookService(BooksRepository rep) {
        this.repository = rep;
    }

    boolean submitForm(Model md) {
        if (repository.findById(md.getIsbn()).isPresent()) { return false; }
        else {
            repository.save(new Book(md.getIsbn(), md.getTitle(), md.getAuthor()));
            return true;
        }
    }

    List<Book> search(String searchText) { return repository.findAllByIsbnContainingOrTitleContainingIgnoreCase(searchText, searchText); }
    List<Book> getBooks() {
        return repository.findAll();
    }

}
