package com.learn.tripmatev2.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;
    private String region;
    private String type;
    private String description;
    private String bestTimeToVisit;
    private BigDecimal averageCostPerDay;
    private String currency;
    private String imageUrl;
    private String visaRequirements;
    private Double safetyRating;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<DestinationAttribute> attributes;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<DestinationActivity> activities;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<Recommendation> recommendations;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<TripHistory> tripHistories;
}
