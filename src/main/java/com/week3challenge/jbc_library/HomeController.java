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
    String bookAvailability="";
    @RequestMapping("/")
    public String homePage(Model model) {
        //model.addAttribute("books",bookRepository.findAll());
        return "index";
    }
    /*
    @RequestMapping("/list")
    public String listBooks(Model model) {
        model.addAttribute("books",bookRepository.findAll());
       // model.addAttribute("book",bookRepository.findAllByAvailabilityContaining(true));
        //model.addAttribute("book",bookRepository.findAllBy(true));
        //model.addAttribute("address",bookRepository.findAllByavailabilityContainingIgnoreCase("Yes"));
        return "list";
    }*/
    @RequestMapping("/list")
    public String bookList(HttpServletRequest request,Model model) {
        model.addAttribute("books",bookRepository.findAll());
        // model.addAttribute("book",bookRepository.findAllByAvailabilityContaining(true));
        //model.addAttribute("book",bookRepository.findAllBy(true));
       // model.addAttribute("address",bookRepository.findAllByavailabilityContainingIgnoreCase("Yes"));
        return "list";
    }
    @RequestMapping("/availablebooks")
    public String availableBooks(HttpServletRequest request,Model model) {
       // book.setAvailability("Yes");
        model.addAttribute("books",bookRepository.findAllByavailabilityContainingIgnoreCase("Yes"));
        return "availablebooks";
    }
    @RequestMapping("/flag/{id}")
    public String flagBook(@PathVariable("id") long id, Model model,Book book){
        //model.addAttribute("book", bookRepository.findOne(id));
       // model.addAttribute("No",bookAvailability);
        book.setAvailability("No");
        return "availablebooks";
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

    @RequestMapping("/borrow")
    public String borrowBooks( @ModelAttribute("book") Book book,Model model) {
        model.addAttribute("books",bookRepository.findAll());
        book.setAvailability("No");
        return "borrow";
    }

/*
    @PostMapping("/borrow")
    public String borrowBookForm(@Valid @ModelAttribute("book") Book book, BindingResult result,@RequestParam("id") long id,Model model,
                                 RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "borrow";
        }
        model.addAttribute("book", bookRepository.findOne(id));
        book.setAvailability("No");
        bookRepository.save(book);
        return "redirect:/list";
    }*/
    /*
    @GetMapping("/borrow")
    public String borrowBook(Model model){
       // model.addAttribute("book",new Book());
        model.addAttribute("books",bookRepository.findAll());
        return "borrow";
    }
    @PostMapping("/update/{id}")
    public String borrowBookForm(@Valid @ModelAttribute("book") Book book, BindingResult result,@PathVariable("id") long id,
                              RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "borrow";
        }
        book.setAvailability("No");
        bookRepository.save(book);
        return "redirect:/list";
    }*/
/*
    @RequestMapping("/borrow")
    public String borrowBooks(Model model) {
        model.addAttribute("books",bookRepository.findAll());
        return "borrow";
    }
  */
    /*
    @RequestMapping("/update/{id}")
    public String borrowBooksUpdate(@PathVariable("id") long id,Book book, Model model){
        model.addAttribute("book", bookRepository.findOne(id));
        book.setAvailability(false);

       // if(book.isAvailability()) {


            model.addAttribute("book", bookRepository.findAll());

        return "/list";
    }*/
    /*
    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public String importParse(@RequestParam("bookImage") Book book) {
        // ... do whatever you want with 'myFile'
        // Redirect to a successful upload page
        byte[]Image1;
        Image1=book.getImage();
        Image1.
        return "redirect:uploadSuccess.html";
    }*/
    /*
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String importParse(@RequestParam("myFile") MultipartFile myFile) {
        // ... do whatever you want with 'myFile'
        // Redirect to a successful upload page
        return "redirect:uploadSuccess.html";
    }*/
/**
    private static String UPLOADED_FOLDER = "../static/images/";
    String imagePath="";

    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("book") Book book, BindingResult result,@RequestParam("bookImage") MultipartFile file,
                              RedirectAttributes redirectAttributes,Model model){
        if(result.hasErrors()){
            return "add";
        }
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        try {
            byte[] bytes = book.getImage();

            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            imagePath=path.toString();
            Files.write(path, bytes);
        }catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("path",imagePath);
        bookRepository.save(book);
        return "redirect:/";
    }
*/

/*
//List all available books first
    @RequestMapping("/borrow")
    public String borrowBookPage(@RequestParam(value = "borrowCheckBox", required = false) String checkboxValue,Model model) {
        model.addAttribute("books",bookRepository.findAll());
        //bookAvailability="Yes";
        //model.addAttribute("bookAvailability",bookAvailability);
        Book book=new Book();
        if(checkboxValue != null)
        {
            book.setAvailability(false);
        }
        return "borrow";
    }
    */
/*
@RequestMapping("/borrow")
public String borrowBookPage(@RequestParam(value = "borrowCheckBox", required = false) String checkboxValue,Model model) {
    model.addAttribute("books",bookRepository.findAll());
    //bookAvailability="Yes";
    //model.addAttribute("bookAvailability",bookAvailability);
    Book book=new Book();
    if(checkboxValue != null)
    {
        //book.setAvailability(false);
        System.out.println(checkboxValue);
    }
    return "borrow";
}
*/
/*
@RequestMapping("/borrow")
public String borrowBook(@RequestParam("borrowCheckBox") Boolean borrowCheckBox,@RequestParam("id") long id,Model model){
    if(borrowCheckBox.equals(1)){
        //bookRepository.findOne(id);
        model.addAttribute("books",bookRepository.findOne(id));
    }

  return "borrow";
}*/

/*
        @GetMapping("/borrow")
        public String borrowBook(Model model){
            model.addAttribute("book",new Book());
            return "borrow";
        }
        */

    /* @RequestParam(value = "checkboxName", required = false) String checkboxValue
        if(checkboxValue != null)
        {
            System.out.println("checkbox is checked");
        }
      else
        {
            System.out.println("checkbox is not checked");
        }
       */
/*
    @PostMapping("/borrow")
    public String borrowBookForm(@Valid @ModelAttribute("book") Book book, BindingResult result,
                              RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "add";
        }

        bookRepository.save(book);
        return "redirect:/";
    }
*/

    @GetMapping("/return")
    public String returnBook(Model model){
        // model.addAttribute("course",new Course());
        return "return";
    }
}
