package com.stary.semestralka;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Controller
@RequestMapping("/Profile")
public class ProfileController {
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String profile(@PathVariable Long id, HttpSession session) {
        User user = userService.getByID(id);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("email", user.getEmail());
        return "Profile";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String YourProfile(HttpSession session) {
        User user = (User) session.getAttribute("user");
        session.setAttribute("username", user.getUsername());
        session.setAttribute("email", user.getEmail());
        return "Profile";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/text")
    public String ChangeProfileData(@RequestBody MultiValueMap<String, String> parameters, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login"; // Redirect to login if not logged in
        }
        if (!userService.checkPassword(user.getId(), parameters.get("currentPassword").get(0))) {
            return "redirect:/Profile";// Redirect to profile if currentPassword was wrong
        }
        if (parameters.get("newPassword").get(0) != null) {
            user.setPassword(BCrypt.hashpw(parameters.get("newPassword").get(0), BCrypt.gensalt())); //encryption of password
        }
        user.setEmail(parameters.get("email").get(0));
        user.setUsername(parameters.get("username").get(0));

        session.setAttribute("email", parameters.get("email").get(0));
        session.setAttribute("username", parameters.get("username").get(0));

        userService.changeData(user);
        return "Profile";
    }
}
