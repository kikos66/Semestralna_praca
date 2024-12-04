package com.stary.semestralka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/Register", "/Login", "/Style.css", "/AboutUs").permitAll() // Publicly accessible
                        .anyRequest().permitAll() // Protect all other endpoints
                )
                .csrf(csrf -> csrf
                        .disable() // Disable CSRF if necessary (e.g., for stateless APIs)
                );



        return http.build();

    }
}

