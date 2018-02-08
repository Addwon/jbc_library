package com.week3challenge.jbc_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/")
    public String homePage(Model model) {
        model.addAttribute("books",bookRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String addBook(Model model){

        return "add";
    }
    @PostMapping("/add")
    public String processForm(@Valid Book book, BindingResult result){
        if(result.hasErrors()){
            return "add";
        }
        bookRepository.save(book);
        return "redirect:/";
    }

    @GetMapping("/borrow")
    public String borrowBook(Model model){

        return "borrow";
    }
    @GetMapping("/return")
    public String returnBook(Model model){
        // model.addAttribute("course",new Course());
        return "return";
    }
}
