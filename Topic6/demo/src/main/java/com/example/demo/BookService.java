package com.example.demo;


import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    private final EntityManager entityManager;

    public BookService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public int createBook(final Book book) {
        Book savedApartment = entityManager.merge(book);
        return savedApartment.getId();
    }

    @Transactional
    public List<Book> findAllBooksWithoutFetch() {
        return entityManager.createQuery("FROM Book", Book.class)
                .getResultList();

    }

    @Transactional
    public List<Book> findByName(String name) {
        return entityManager.createQuery("Select a FROM Book a Where a.title = :nam").setParameter("nam", name).getResultList();

    }
    @Transactional
    public List<Book> findByIsbn(String name) {
        return entityManager.createQuery("Select a FROM Book a Where a.isbn = :nam").setParameter("nam", name).getResultList();

    }
    @Transactional
    public List<Book> findByAuthor(String name) {
        return entityManager.createQuery("Select a FROM Book a Where a.author = :nam").setParameter("nam", name).getResultList();

    }


    @Transactional
    public Book getBookById(int bookId) {
        return entityManager.find(Book.class, bookId);
    }

}
