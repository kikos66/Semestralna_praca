package com.stary.semestralka;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.IllegalArgumentException;

@Controller
@RequestMapping("/Login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String login() {
        System.out.println("xd");
        return "Login";
    }

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/text")
    public String loginPost(@RequestBody MultiValueMap<String, String> parameters, Model model, HttpSession session) {
        System.out.println("xd2");
        User user = userService.authenticate(parameters.get("email").get(0), parameters.get("password").get(0));
        if (user != null) {
            session.setAttribute(user.getUsername(), user);
            System.out.println(user.getUsername());
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "Login";
        }
    }
}
