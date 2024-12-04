package com.stary.semestralka;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.IllegalArgumentException;
import org.springframework.security.crypto.bcrypt.BCrypt;


@Controller
@RequestMapping("/Register")
public class RegistrationController {

    @RequestMapping(method = RequestMethod.GET)
    public String Register() {
        return "Register";
    }

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/text")
    public String RegisterPost(@RequestBody MultiValueMap<String, String> parameters, Model model) {

        if (parameters.get("email").get(0).isEmpty() ||
                parameters.get("username").get(0).isEmpty() ||
                parameters.get("password").get(0).isEmpty()) {
            model.addAttribute("error", "All fields are required!");
            return "Register";
        }
        try {
            User user = new User();
            user.setEmail(parameters.get("email").get(0));
            user.setUsername(parameters.get("username").get(0));
            user.setPassword(BCrypt.hashpw(parameters.get("password").get(0), BCrypt.gensalt())); //encryption of password
            userService.registerUser(user);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "Register";
        }

        return "redirect:/Login";
    }
}
