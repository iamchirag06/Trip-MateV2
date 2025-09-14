package com.learn.tripmatev2.repository;

import com.learn.tripmatev2.model.TripHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripHistoryRepository extends JpaRepository<TripHistory, Long> {
}
