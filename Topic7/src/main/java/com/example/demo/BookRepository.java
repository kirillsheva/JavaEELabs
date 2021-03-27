package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByAuthorLike(String contains);
    List<Book> findAllByTitleLike(String contains);
    List<Book> findAllByIsbnLike(String contains);


    @Query("SELECT u FROM Book u " + "WHERE u.title LIKE :param1 " + "OR u.isbn LIKE :param1")
    List<Book> findAllByIsbnOrName(@Param("param1") String param1);

}