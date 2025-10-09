package com.learn.tripmatev2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "destinations", indexes = {
    @Index(name = "idx_dest_country", columnList = "country"),
    @Index(name = "idx_dest_type", columnList = "type"),
    @Index(name = "idx_dest_safety", columnList = "safetyRating"),
    @Index(name = "idx_dest_popularity", columnList = "popularityScore")
})
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Destination name is required")
    @Size(max = 200)
    private String name;

    @NotBlank(message = "Country is required")
    @Size(max = 100)
    private String country;

    @Size(max = 100)
    private String region;

    @Size(max = 100)
    private String type; // Beach, Mountain, City, Historical, Wildlife, Adventure

    @Size(max = 2000)
    private String description;

    @Size(max = 200)
    private String bestTimeToVisit; // e.g., "April-October", "Winter", "Year-round"

    @PositiveOrZero
    private BigDecimal averageCostPerDay;

    @Size(max = 10)
    private String currency;

    @Size(max = 500)
    private String imageUrl;

    @Size(max = 500)
    private String visaRequirements;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    private Double safetyRating; // 0-10 scale

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    private Double popularityScore; // 0-10 scale for recommendation algorithm

    @Size(max = 100)
    private String climate; // Tropical, Temperate, Cold, Desert, Mediterranean

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DestinationAttribute> attributes = new ArrayList<>();

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DestinationActivity> activities = new ArrayList<>();

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<Recommendation> recommendations = new ArrayList<>();

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<TripHistory> tripHistories = new ArrayList<>();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBestTimeToVisit() {
        return bestTimeToVisit;
    }

    public void setBestTimeToVisit(String bestTimeToVisit) {
        this.bestTimeToVisit = bestTimeToVisit;
    }

    public BigDecimal getAverageCostPerDay() {
        return averageCostPerDay;
    }

    public void setAverageCostPerDay(BigDecimal averageCostPerDay) {
        this.averageCostPerDay = averageCostPerDay;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVisaRequirements() {
        return visaRequirements;
    }

    public void setVisaRequirements(String visaRequirements) {
        this.visaRequirements = visaRequirements;
    }

    public Double getSafetyRating() {
        return safetyRating;
    }

    public void setSafetyRating(Double safetyRating) {
        this.safetyRating = safetyRating;
    }

    public Double getPopularityScore() {
        return popularityScore;
    }

    public void setPopularityScore(Double popularityScore) {
        this.popularityScore = popularityScore;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
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

    public List<DestinationAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<DestinationAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<DestinationActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<DestinationActivity> activities) {
        this.activities = activities;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public List<TripHistory> getTripHistories() {
        return tripHistories;
    }

    public void setTripHistories(List<TripHistory> tripHistories) {
        this.tripHistories = tripHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination that = (Destination) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Destination{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", type='" + type + '\'' +
                ", popularityScore=" + popularityScore +
                '}';
    }
}
