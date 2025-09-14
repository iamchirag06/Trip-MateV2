package com.learn.tripmatev2.repository;


import com.learn.tripmatev2.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}

