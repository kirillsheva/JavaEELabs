
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
        model.addAttribute("bookStorage",  DemoApplication.bookService.findAllBooksWithoutFetch());
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
        int x= Integer.parseInt(type);
        if (x != 0) if (x == 1)
            model.addAttribute("bookStorage", DemoApplication.bookService.findByIsbn(value));
        else
            model.addAttribute("bookStorage", DemoApplication.bookService.findByAuthor(value));
        else {
            model.addAttribute("bookStorage", DemoApplication.bookService.findByName(value));
        }
        return "List";
    }

}