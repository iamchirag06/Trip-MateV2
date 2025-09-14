package com.learn.tripmatev2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "destination_attributes")
public class DestinationAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String attributeType;
    private String attributeValue;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;
}
