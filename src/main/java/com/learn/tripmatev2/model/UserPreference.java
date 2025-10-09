package com.learn.tripmatev2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_preferences", indexes = {
    @Index(name = "idx_pref_user", columnList = "user_id"),
    @Index(name = "idx_pref_type", columnList = "tripType"),
    @Index(name = "idx_pref_climate", columnList = "preferredClimate")
})
public class UserPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    private String tripType; // Solo, Family, Couple, Group, Business

    @Size(max = 100)
    private String preferredClimate; // Tropical, Temperate, Cold, Desert, Mediterranean

    @Size(max = 50)
    private String activityLevel; // Low, Moderate, High, Very High

    @Size(max = 500)
    private String accessibilityNeeds;

    @Size(max = 500)
    private String dietaryRestrictions;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getPreferredClimate() {
        return preferredClimate;
    }

    public void setPreferredClimate(String preferredClimate) {
        this.preferredClimate = preferredClimate;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getAccessibilityNeeds() {
        return accessibilityNeeds;
    }

    public void setAccessibilityNeeds(String accessibilityNeeds) {
        this.accessibilityNeeds = accessibilityNeeds;
    }

    public String getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(String dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPreference that = (UserPreference) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserPreference{" +
                "id=" + id +
                ", tripType='" + tripType + '\'' +
                ", preferredClimate='" + preferredClimate + '\'' +
                ", activityLevel='" + activityLevel + '\'' +
                '}';
    }
}

