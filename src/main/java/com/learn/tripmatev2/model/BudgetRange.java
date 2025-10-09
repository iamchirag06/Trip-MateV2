package com.learn.tripmatev2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "budget_ranges", indexes = {
    @Index(name = "idx_budget_user", columnList = "user_id"),
    @Index(name = "idx_budget_type", columnList = "tripType")
})
public class BudgetRange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Trip type is required")
    @Size(max = 100)
    private String tripType;

    @NotNull(message = "Minimum budget is required")
    @PositiveOrZero
    private BigDecimal minBudget;

    @NotNull(message = "Maximum budget is required")
    @Positive
    private BigDecimal maxBudget;

    @NotBlank(message = "Currency is required")
    @Size(max = 10)
    private String currency;

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

    public BigDecimal getMinBudget() {
        return minBudget;
    }

    public void setMinBudget(BigDecimal minBudget) {
        this.minBudget = minBudget;
    }

    public BigDecimal getMaxBudget() {
        return maxBudget;
    }

    public void setMaxBudget(BigDecimal maxBudget) {
        this.maxBudget = maxBudget;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
        BudgetRange that = (BudgetRange) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BudgetRange{" +
                "id=" + id +
                ", tripType='" + tripType + '\'' +
                ", minBudget=" + minBudget +
                ", maxBudget=" + maxBudget +
                ", currency='" + currency + '\'' +
                '}';
    }
}
