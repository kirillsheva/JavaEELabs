package com.example.demo;

import com.example.demo.Service.BookService;
import com.example.demo.Service.PermissionService;
import com.example.demo.Service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {
public static BookService bookService;
public static UserService userService;
static PermissionService permissionService;
	public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
         bookService = applicationContext.getBean(BookService.class);
         userService = applicationContext.getBean(UserService.class);
         permissionService = applicationContext.getBean(PermissionService.class);

       }

}
