package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, String> {

    List<Book> findAllByIsbnContainingOrTitleContainingIgnoreCase(String isbn, String title);
}
