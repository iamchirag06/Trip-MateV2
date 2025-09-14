package com.learn.tripmatev2.repository;

import com.learn.tripmatev2.model.TripActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripActivityRepository extends JpaRepository<TripActivity, Long> {
}
