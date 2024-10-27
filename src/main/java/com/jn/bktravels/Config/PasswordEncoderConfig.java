package com.jn.bktravels.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    // Create a PasswordEncoder bean using BCryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Method to encode a password
    public String encodePassword(String rawPassword) {
        return passwordEncoder().encode(rawPassword);
    }

    // Method to match a raw password with an encoded password
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder().matches(rawPassword, encodedPassword);
    }
}
