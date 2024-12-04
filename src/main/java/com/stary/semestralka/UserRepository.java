package com.stary.semestralka;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    User findByEmail(String email);
    User findByUserId (Long id);
}
