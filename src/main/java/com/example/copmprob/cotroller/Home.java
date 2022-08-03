package com.example.copmprob.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/contacts")
    public String contact(){
        return "contacts";
    }

    @GetMapping("/intro")
    public String intro(){
        return "intro";
    }

}
