package com.learn.tripmatev2.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "recommendations")
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tripDuration;
    private BigDecimal totalEstimatedCost;
    private String currency;
    private Double matchScore;
    private String recommendationReason;
    private Boolean saved;
    private LocalDateTime generatedAt;
    private LocalDateTime viewedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @OneToMany(mappedBy = "recommendation", cascade = CascadeType.ALL)
    private List<RecommendationActivity> activities;
}
