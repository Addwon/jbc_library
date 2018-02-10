package com.week3challenge.jbc_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
//import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
//import org.springframework.context.annotation.Bean;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class HomeController {
    @Autowired
    BookRepository bookRepository;

    String bookBorrowMessage="";
    String bookRetrunMessage="";

    @RequestMapping("/")
    public String homePage(Model model) {

        return "index";
    }

    @RequestMapping("/list")
    public String bookList(HttpServletRequest request,Model model) {
        model.addAttribute("books",bookRepository.findAll());
        // model.addAttribute("book",bookRepository.findAllByAvailabilityContaining(true));
        //model.addAttribute("book",bookRepository.findAllBy(true));
       // model.addAttribute("address",bookRepository.findAllByavailabilityContainingIgnoreCase("Yes"));
        return "list";
    }

    @GetMapping("/add")
    public String addBook(Model model){
        model.addAttribute("book",new Book());
        return "add";
    }
    @PostMapping("/process")
    public String addBookForm(@Valid @ModelAttribute("book") Book book, BindingResult result,
                              RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "add";
        }
        book.setAvailability("Yes");
        bookRepository.save(book);
        return "redirect:/";
    }

    @RequestMapping("/borrow/{id}")
    public String borrowBook(@PathVariable("id") long id,Model model,RedirectAttributes redirectAttributes ){
        Book book=bookRepository.findOne(id);
        //bookBorrowMessage=book.getTitle()+" is borrowed";
        //model.addAttribute("bookBorrowMessage",bookBorrowMessage);
        //redirAttrs.addFlashAttribute("message", "This is borrowed");
        bookBorrowMessage="\""+book.getTitle()+"\""+" is borrowed";

        redirectAttributes.addFlashAttribute("message1", bookBorrowMessage);

        book.setAvailability("No");
        model.addAttribute("book", bookRepository.findOne(id));
        bookRepository.save(book);
        return "redirect:/";
    }

    @RequestMapping("/return/{id}")
    public String returnBook(@PathVariable("id") long id,Model model,RedirectAttributes redirectAttributes){
        Book book=bookRepository.findOne(id);
        bookRetrunMessage="\""+book.getTitle()+"\""+" is returned";
        redirectAttributes.addFlashAttribute("message2", bookRetrunMessage);
        book.setAvailability("Yes");
        model.addAttribute("book", bookRepository.findOne(id));
        bookRepository.save(book);
        return "redirect:/";
    }

    @RequestMapping("/borrow")
    public String availableBooks(HttpServletRequest request,Model model) {
        model.addAttribute("books",bookRepository.findAllByavailabilityContainingIgnoreCase("Yes"));
        return "borrow";
    }
    @RequestMapping("/return")
    public String borrowedBooks(HttpServletRequest request,Model model) {
        model.addAttribute("books",bookRepository.findAllByavailabilityContainingIgnoreCase("No"));
        return "return";
    }

}
