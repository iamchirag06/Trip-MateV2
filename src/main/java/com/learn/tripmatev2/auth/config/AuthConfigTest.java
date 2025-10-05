package com.learn.tripmatev2.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class AuthConfigTest {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.github.client-id}")
    private String githubClientId;

    @Bean
    public CommandLineRunner testConfig() {
        return args -> {
            System.out.println("\n=== OAuth2 Configuration Test ===");
            System.out.println("Google Client ID configured: " + (googleClientId != null && !googleClientId.isEmpty()));
            System.out.println("GitHub Client ID configured: " + (githubClientId != null && !githubClientId.isEmpty()));
            System.out.println("===============================\n");
        };
    }
}