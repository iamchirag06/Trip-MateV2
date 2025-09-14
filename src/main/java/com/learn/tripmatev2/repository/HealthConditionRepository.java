package com.learn.tripmatev2.repository;

import com.learn.tripmatev2.model.HealthCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthConditionRepository extends JpaRepository<HealthCondition, Long> {
}

