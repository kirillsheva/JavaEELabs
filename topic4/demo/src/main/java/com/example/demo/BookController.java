
package com.example.demo;
import com.example.demo.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/List")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }


    @GetMapping("")
    public List<Book> search(@RequestParam(value = "value", required = false) String value) {
        return value != null ? service.search(value) : service.getBooks();
    }

    @PostMapping("/")
    public ResponseEntity<Object> submitForm(@RequestBody Model model) {
        return service.submitForm(model) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
