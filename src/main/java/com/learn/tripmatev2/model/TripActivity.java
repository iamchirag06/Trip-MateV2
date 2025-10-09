package com.learn.tripmatev2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "trip_activities", indexes = {
    @Index(name = "idx_tripact_trip", columnList = "trip_id"),
    @Index(name = "idx_tripact_activity", columnList = "activity_id"),
    @Index(name = "idx_tripact_rating", columnList = "enjoymentRating")
})
public class TripActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Max(5)
    private Integer enjoymentRating; // 1-5 star rating

    private LocalDateTime createdAt;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private TripHistory trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

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

    public Integer getEnjoymentRating() {
        return enjoymentRating;
    }

    public void setEnjoymentRating(Integer enjoymentRating) {
        this.enjoymentRating = enjoymentRating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public TripHistory getTrip() {
        return trip;
    }

    public void setTrip(TripHistory trip) {
        this.trip = trip;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripActivity that = (TripActivity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TripActivity{" +
                "id=" + id +
                ", enjoymentRating=" + enjoymentRating +
                '}';
    }
}
