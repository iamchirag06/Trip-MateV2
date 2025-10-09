package com.learn.tripmatev2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "recommendations", indexes = {
    @Index(name = "idx_rec_user", columnList = "user_id"),
    @Index(name = "idx_rec_dest", columnList = "destination_id"),
    @Index(name = "idx_rec_score", columnList = "matchScore"),
    @Index(name = "idx_rec_generated", columnList = "generatedAt"),
    @Index(name = "idx_rec_saved", columnList = "saved")
})
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Trip duration is required")
    @Min(1)
    @Max(365)
    private Integer tripDurationDays; // Duration in days

    @PositiveOrZero
    private BigDecimal totalEstimatedCost;

    @Size(max = 10)
    private String currency;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100.0")
    private Double matchScore; // 0-100 percentage match

    @Size(max = 1000)
    private String recommendationReason;

    @NotNull
    private Boolean saved = false;

    @NotNull
    private LocalDateTime generatedAt;

    private LocalDateTime viewedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    @OneToMany(mappedBy = "recommendation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecommendationActivity> activities = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (generatedAt == null) {
            generatedAt = LocalDateTime.now();
        }
        if (saved == null) {
            saved = false;
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTripDurationDays() {
        return tripDurationDays;
    }

    public void setTripDurationDays(Integer tripDurationDays) {
        this.tripDurationDays = tripDurationDays;
    }

    public BigDecimal getTotalEstimatedCost() {
        return totalEstimatedCost;
    }

    public void setTotalEstimatedCost(BigDecimal totalEstimatedCost) {
        this.totalEstimatedCost = totalEstimatedCost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(Double matchScore) {
        this.matchScore = matchScore;
    }

    public String getRecommendationReason() {
        return recommendationReason;
    }

    public void setRecommendationReason(String recommendationReason) {
        this.recommendationReason = recommendationReason;
    }

    public Boolean getSaved() {
        return saved;
    }

    public void setSaved(Boolean saved) {
        this.saved = saved;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public LocalDateTime getViewedAt() {
        return viewedAt;
    }

    public void setViewedAt(LocalDateTime viewedAt) {
        this.viewedAt = viewedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public List<RecommendationActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<RecommendationActivity> activities) {
        this.activities = activities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recommendation that = (Recommendation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "id=" + id +
                ", tripDurationDays=" + tripDurationDays +
                ", matchScore=" + matchScore +
                ", saved=" + saved +
                '}';
    }
}
