package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BookDTO {
    private Integer id;

    @NotEmpty(message = "Name of the book is empty!")
    private String name;
    @NotEmpty(message = "ISBN is empty!")
    @Pattern(regexp = "[0-9]*[-| ][0-9]*[-| ][0-9]*[-| ][0-9]*[-| ][0-9]*", message = "You have to match the ISBN standard")
    private String isbn;
    @NotEmpty(message = "Author is empty!")
    private String author;
}
