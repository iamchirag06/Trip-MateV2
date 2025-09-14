package com.learn.tripmatev2.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "recommendation_activities")
public class RecommendationActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer dayNumber;
    private String timeOfDay;
    private BigDecimal estimatedCost;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "recommendation_id")
    private Recommendation recommendation;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;
}

