package com.example.demo.Controller;

import com.example.demo.BookDTO;
import com.example.demo.DemoApplication;
import com.example.demo.Entity.BookEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @RequestMapping(value = "/booklist", method = RequestMethod.GET)
    public ResponseEntity<List<BookDTO>> getBooks(@RequestParam (name="valu", required = false) final String value, @RequestParam (name="typ", required = false) String type) {
        List<BookDTO> res=new ArrayList<>();
        List<BookEntity> list;
        if(value==null)
        {
           list = DemoApplication.bookService.getAllBooks();
            System.out.println();
            System.out.println();
            System.out.println(list.size());
            System.out.println();
            System.out.println();
        }
        else{

            int x=Integer.parseInt(type);
            System.out.println(x);
            if(x==0)
                list = DemoApplication.bookService.findByName(value);
            else if (x==1)
                list = DemoApplication.bookService.findByIsbn(value);
            else if (x==2)
                list = DemoApplication.bookService.findByAuthor(value);
            else
                list = DemoApplication.bookService.findByNameOrIsbn(value);

        }

        for(BookEntity b : list)
        {
            res.add(new BookDTO(b.getId(), b.getBookname(), b.getIsbn(), b.getAuthor()));
res.get(res.size()-1).getName();
              }
        // code to get books and enrich model with those books
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/bookpage/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable("id") Integer id) {
        BookEntity b = DemoApplication.bookService.getBookById(id);
        BookDTO book=new BookDTO(b.getId(),b.getBookname(),b.getIsbn(),b.getAuthor());
        return ResponseEntity.ok(book);

    }
    @GetMapping("/book_page/{id}")
    public String getBookPage(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("book", DemoApplication.bookService.getBookById(id));
//        model.addAttribute("isFavorite", DemoApplication.userService.isFavorite(id, prins);
        return "book_page";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/booklist", method = RequestMethod.POST)
    public ResponseEntity<BookDTO> formControllerPost(@RequestBody final BookDTO book) {
        BookEntity ben = new BookEntity();
        ben.setBookname(book.getName());
        ben.setAuthor(book.getAuthor());
        ben.setIsbn(book.getIsbn());
       BookEntity b= DemoApplication.bookService.createBook(ben);
        System.out.println(b);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }



}
