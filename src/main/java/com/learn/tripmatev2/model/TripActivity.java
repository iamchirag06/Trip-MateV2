package com.learn.tripmatev2.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trip_activities")
public class TripActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer enjoymentRating;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private TripHistory trip;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;
}
