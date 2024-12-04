package com.stary.semestralka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        userRepository.save(user);
        System.out.println("User " + user.getId() + " registered successfully");
    }

    public User authenticate(String email, String password) {
        System.out.println("xd1");
        User user = userRepository.findByEmail(email);
        if (BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}
