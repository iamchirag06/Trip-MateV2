package com.learn.tripmatev2.repository;


import com.learn.tripmatev2.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
}

