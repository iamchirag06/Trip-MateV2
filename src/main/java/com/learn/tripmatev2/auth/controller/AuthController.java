package com.learn.tripmatev2.auth.controller;

import com.learn.tripmatev2.auth.user.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal == null) {
            return ResponseEntity.ok(Map.of("authenticated", false));
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("authenticated", true);
        response.put("id", userPrincipal.getId());
        response.put("email", userPrincipal.getUsername());
        response.put("roles", userPrincipal.getAuthorities());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        // Spring Security handles the logout process
        return ResponseEntity.ok().build();
    }
}