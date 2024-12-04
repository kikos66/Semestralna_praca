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
                        .anyRequest().authenticated() // Protect all other endpoints
                )
                .formLogin(form -> form
                        .loginPage("/Login") // Custom login page
                        .permitAll()
                );

        return http.build();

    }
}
