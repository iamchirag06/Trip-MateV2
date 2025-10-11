package com.learn.tripmatev2.model;

import com.learn.tripmatev2.auth.AuthProvider;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_auth_logs", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "auth_provider", "ip_address"}, 
                     name = "uk_auth_log_user_provider_ip")
})
public class UserAuthLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "auth_provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "login_time", nullable = false)
    private LocalDateTime loginTime;

    @Column(name = "success", nullable = false)
    private boolean success;

    public UserAuthLog() {
    }

    public UserAuthLog(User user, AuthProvider authProvider, String ipAddress, String userAgent, boolean success) {
        this.user = user;
        this.authProvider = authProvider;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.success = success;
        this.loginTime = LocalDateTime.now();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuthProvider getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}