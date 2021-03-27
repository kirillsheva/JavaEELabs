package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class BookController {

    @RequestMapping({ "/", "" })
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/List", method = RequestMethod.GET)
    public String booksList(@ModelAttribute Book formModel, Model model) {
        model.addAttribute("bookStorage",  DemoApplication.bookService.getAllBooks());
        return "List";

    }


    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public String formControllerPost(@ModelAttribute Book formModel) {
        DemoApplication.bookService.createBook(formModel);
        return "redirect:/List";
    }
    @RequestMapping(value = "/exact-book", method = RequestMethod.POST)
    public String booksList(@RequestParam int bookId) {
        return "redirect:/book/"+bookId;
    }

    @RequestMapping({"/book/{id}", ""})
    public String bookById(@PathVariable String id, @ModelAttribute Book b, Model model) {
        int bookId = Integer.parseInt(id);
        model.addAttribute("book",DemoApplication.bookService.getBookById(bookId));
        return "Book";
    }
    @RequestMapping(value = "/filtered-book-list", method = RequestMethod.GET)
    public String bookList(@RequestParam String value, String type, @ModelAttribute Book formModel, Model model) {
        int x = Integer.parseInt(type);
        switch (x) {
            case 0:
                model.addAttribute("bookStorage", DemoApplication.bookService.findByName(value));
                break;
            case 1:
                model.addAttribute("bookStorage", DemoApplication.bookService.findByIsbn(value));
                break;
            case 2:
                model.addAttribute("bookStorage", DemoApplication.bookService.findByAuthor(value));
                break;
            case 3:
                model.addAttribute("bookStorage", DemoApplication.bookService.findByNameOrIsbn(value));
                break;
        }
        return "List";
    }

}