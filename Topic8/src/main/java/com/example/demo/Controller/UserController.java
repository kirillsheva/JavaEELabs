package com.example.demo.Controller;

import com.example.demo.BookDTO;
import com.example.demo.DemoApplication;
import com.example.demo.Entity.BookEntity;
import com.example.demo.MyPasswordEncoder;
import com.example.demo.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;
@Controller
public class UserController {

@RequestMapping(value="/signup_page", method = RequestMethod.GET)
public String getRegisterPage() {
    return "signup_page";
}


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody final UserDTO user) {
        if (DemoApplication.userService.userExists(user.getLogin())) {
            return new ResponseEntity<>("Username is taken.",
                    HttpStatus.FORBIDDEN);
        }
        String userPassword = user.getPassword();
        MyPasswordEncoder encoder = new MyPasswordEncoder();
        String encodedPassword = encoder.encode(userPassword);
        user.setPassword(encodedPassword);
        DemoApplication.userService.registerUser(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/booklist/favorites", method = RequestMethod.GET)
    public ResponseEntity<List<BookDTO>> getFavorites(final Principal principal) {
        String login = principal.getName();
        List<BookEntity> favorites = DemoApplication.userService.findFavorites(login);
        List<BookDTO> response = new ArrayList<>();
        for( BookEntity b : favorites)
        {
            response.add(new BookDTO(b.getId(), b.getBookname(), b.getIsbn(), b.getAuthor()));
        }
        return ok(response);
    }
    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/booklist/favorites/add", method = RequestMethod.PUT)
    public ResponseEntity addFavorites(final Principal principal, @RequestBody final BookDTO book) {
        String login = principal.getName();
        DemoApplication.userService.addFavorites(BookEntity.builder().id(book.getId()).bookname(book.getName()).author(book.getAuthor()).isbn(book.getIsbn()).build(), login);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/booklist/favorites/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteFavorites(final Principal principal, @RequestBody final BookDTO book) {
        String login = principal.getName();
        DemoApplication.userService.deleteFavorites(book.getId(), login);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/booklist/isFavorite/{id}" , method = RequestMethod.GET)
    public ResponseEntity<Boolean> isFavorite(final Principal principal, @PathVariable("id") final int id) {
        if (principal == null) {
            return ok(false);
        }
        boolean fav=DemoApplication.userService.isFavorite(id, principal.getName());
        return ok().body(fav);
    }
}
