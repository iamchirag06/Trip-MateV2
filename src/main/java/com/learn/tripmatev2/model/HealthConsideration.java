package com.learn.tripmatev2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "health_considerations")
public class HealthConsideration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String considerationType;
    private String considerationValue;
    private String recommendation;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;
}

