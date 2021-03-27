package com.example.demo;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository rep;
    @Transactional
    public void createBook(final Book book) {
        rep.saveAndFlush(book);
    }

    @Transactional
    public List<Book> getAllBooks() {
        return rep.findAll();

    }

    @Transactional
    public List<Book> findByName(String name) {
        return rep.findAllByTitleLike(name);
    }
    @Transactional
    public List<Book> findByIsbn(String name) {
        return rep.findAllByIsbnLike(name);
    }
    @Transactional
    public List<Book> findByAuthor(String name) {
        return rep.findAllByAuthorLike(name);
    }
    @Transactional
    public List<Book> findByNameOrIsbn(String name) {
        return rep.findAllByIsbnOrName(name);
    }



    @Transactional
    public Book getBookById(int id) {
        Optional<Book> optionalBook = rep.findById(id);

        return optionalBook
                .orElse(null);
    }

}