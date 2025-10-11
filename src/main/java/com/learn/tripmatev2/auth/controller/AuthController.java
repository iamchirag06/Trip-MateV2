package com.learn.tripmatev2.auth.controller;

import com.learn.tripmatev2.auth.user.UserPrincipal;
import com.learn.tripmatev2.model.UserAuthLog;
import com.learn.tripmatev2.repository.UserAuthLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserAuthLogRepository userAuthLogRepository;

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
        
        // Add auth log information
        List<UserAuthLog> authLogs = userAuthLogRepository.findByUserIdOrderByLoginTimeDesc(userPrincipal.getId());
        response.put("authLogs", authLogs);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        // Spring Security handles the logout process
        return ResponseEntity.ok().build();
    }
}