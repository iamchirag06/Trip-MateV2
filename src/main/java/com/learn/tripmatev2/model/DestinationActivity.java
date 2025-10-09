package com.learn.tripmatev2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "destination_activities", indexes = {
    @Index(name = "idx_destact_dest", columnList = "destination_id"),
    @Index(name = "idx_destact_activity", columnList = "activity_id")
})
public class DestinationActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    private String bestTimeOfDay; // Morning, Afternoon, Evening, Night, Anytime

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

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

    public String getBestTimeOfDay() {
        return bestTimeOfDay;
    }

    public void setBestTimeOfDay(String bestTimeOfDay) {
        this.bestTimeOfDay = bestTimeOfDay;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
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
        DestinationActivity that = (DestinationActivity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DestinationActivity{" +
                "id=" + id +
                ", bestTimeOfDay='" + bestTimeOfDay + '\'' +
                '}';
    }
}
