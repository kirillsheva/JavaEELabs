package com.example.demo.Service;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Repository.BookRepository;
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
    public BookEntity createBook(final BookEntity book) {
        return rep.saveAndFlush(book);
    }

    @Transactional
    public List<BookEntity> getAllBooks() {
        return rep.findAll();

    }

    @Transactional
    public List<BookEntity> findByName(String name) {
        return rep.findAllByBooknameLike(name);
    }
    @Transactional
    public List<BookEntity> findByIsbn(String name) {
        return rep.findAllByIsbnLike(name);
    }
    @Transactional
    public List<BookEntity> findByAuthor(String name) {
        return rep.findAllByAuthorLike(name);
    }
    @Transactional
    public List<BookEntity> findByNameOrIsbn(String name) {
        return rep.findAllByIsbnOrName(name);
    }

    @Transactional
    public BookEntity getBookById(int id) {
        return rep.findById(id).orElse(null);

    }

}
