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
        User user = userRepository.findByEmail(email);
        if (BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public User getByID(Long id) {
        return userRepository.findByUserId(id);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public boolean checkPassword(Long id, String password) {
        User user = userRepository.findByUserId(id);
        if (BCrypt.checkpw(password, user.getPassword())) {
            return true;
        }
        return false;
    }

    public void changeData(User user) {
        userRepository.save(user);
    }
}
