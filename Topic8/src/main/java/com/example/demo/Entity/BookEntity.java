package com.example.demo.Entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;

import java.util.List;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BookEntity
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "bookname")
    private String bookname;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "author")
    private String author;

    @ManyToMany
    @JoinTable(
            name = "users_to_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )    private List<UserEntity> users;


}