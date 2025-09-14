package com.learn.tripmatev2.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "destination_activities")
public class DestinationActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bestTimeOfDay;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;
}
