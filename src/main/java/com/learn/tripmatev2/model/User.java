package com.learn.tripmatev2.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String fullName;
    private LocalDate dob;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPreference> preferences;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<HealthCondition> healthConditions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BudgetRange> budgetRanges;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TripHistory> tripHistories;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Recommendation> recommendations;
}
