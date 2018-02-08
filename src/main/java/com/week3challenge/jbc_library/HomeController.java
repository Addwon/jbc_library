package com.week3challenge.jbc_library;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String helloWorld(Model model) {
        return "list";
    }
}
