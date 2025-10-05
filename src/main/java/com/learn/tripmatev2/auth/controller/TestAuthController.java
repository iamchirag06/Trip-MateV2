package com.learn.tripmatev2.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestAuthController {

    @GetMapping("/public")
    public Map<String, String> publicEndpoint() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "This is a public endpoint");
        return response;
    }

    @GetMapping("/private")
    public Map<String, Object> privateEndpoint(@AuthenticationPrincipal OAuth2User principal) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "This is a private endpoint");
        response.put("name", principal.getAttribute("name"));
        response.put("email", principal.getAttribute("email"));
        return response;
    }
}