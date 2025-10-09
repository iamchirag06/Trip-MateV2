package com.learn.tripmatev2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "activities", indexes = {
    @Index(name = "idx_activity_category", columnList = "category"),
    @Index(name = "idx_activity_intensity", columnList = "intensityLevel")
})
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Activity name is required")
    @Size(max = 200)
    private String name;

    @Size(max = 100)
    private String category; // e.g., Adventure, Cultural, Relaxation, Food, Nature

    @Size(max = 50)
    private String intensityLevel; // Low, Medium, High

    @Size(max = 50)
    private String accessibilityRating; // Excellent, Good, Fair, Limited

    @PositiveOrZero
    private BigDecimal costEstimate;

    @Size(max = 1000)
    private String description;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIntensityLevel() {
        return intensityLevel;
    }

    public void setIntensityLevel(String intensityLevel) {
        this.intensityLevel = intensityLevel;
    }

    public String getAccessibilityRating() {
        return accessibilityRating;
    }

    public void setAccessibilityRating(String accessibilityRating) {
        this.accessibilityRating = accessibilityRating;
    }

    public BigDecimal getCostEstimate() {
        return costEstimate;
    }

    public void setCostEstimate(BigDecimal costEstimate) {
        this.costEstimate = costEstimate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(id, activity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", intensityLevel='" + intensityLevel + '\'' +
                '}';
    }
}

