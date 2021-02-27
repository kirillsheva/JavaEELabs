package com.example.demo;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    private BooksRepository repository;

    public BookController(BooksRepository repository) { this.repository = repository; }

    @GetMapping("/Form")
    public String mainPage() {
        return "Form";
    }

    @GetMapping("/List")
    public String getList(org.springframework.ui.Model md) {
        md.addAttribute("list", repository.findAll(Sort.by(Sort.Order.asc("title"))));
        return "List";
    }

    @PostMapping("/Form")
    public String submitForm(@ModelAttribute Model md, Book book) {
        if(md.getIsbn().isEmpty() || md.getAuthor().isEmpty() || md.getTitle().isEmpty()){
            return "Form";
        }
        BeanUtils.copyProperties(md, book,"");
        repository.save(book);
        return "redirect:/List";
    }


}