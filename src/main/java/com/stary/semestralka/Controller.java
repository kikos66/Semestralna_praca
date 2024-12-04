package com.stary.semestralka;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/AboutUs")
    public String AboutUs() {
        return "AboutUS";
    }
}
