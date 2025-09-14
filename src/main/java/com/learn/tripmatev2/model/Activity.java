package com.learn.tripmatev2.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String intensityLevel;
    private String accessibilityRating;
    private BigDecimal costEstimate;
    private String description;
    private LocalDateTime createdAt;
}

