package com.baizhi.controller;

import com.baizhi.entity.Book;
import com.baizhi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {


    @Autowired
    private BookService bookService;


    @RequestMapping("/find")
    public String find(String keywords, Model model){
        System.out.println(keywords);
        List<Book> books = bookService.find(keywords);
        for (Book book : books) {
            System.out.println(book);
        }
        model.addAttribute("books",books);
        model.addAttribute("keywords",keywords);
        return "search";
    }


}
