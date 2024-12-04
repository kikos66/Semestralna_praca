package com.stary.semestralka;

import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

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
    @GetMapping("/Logout")
    public String Logout(HttpSession session) {
        if (session.getAttribute("user") != null) {
            session.setAttribute("user", null);
        }
        return "redirect:/";
    }

    @Autowired
    private UserService userService;

    //TODO: move to ProfileController
    @GetMapping(value = "/DeleteAccount")
    public String deleteAccount(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            userService.deleteUserById(user.getId());
            session.invalidate();
        }

        return "redirect:/";
        }
}

