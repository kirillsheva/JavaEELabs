package com.example.demo.Repository;

import java.util.List;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    List<BookEntity> findAllByAuthorLike(String contains);
    List<BookEntity> findAllByBooknameLike(String contains);
    List<BookEntity> findAllByIsbnLike(String contains);

    @Query("SELECT u FROM BookEntity u "
            + "WHERE u.bookname LIKE :param1 "
            + "OR u.isbn LIKE :param1")
    List<BookEntity> findAllByIsbnOrName(@Param("param1") String param1);


    @Query("SELECT book FROM BookEntity book "
            + "JOIN book.users user "
            + "WHERE user.login = :login")
    List<BookEntity> findFavoritesForUser(@Param("login") final String login);


}