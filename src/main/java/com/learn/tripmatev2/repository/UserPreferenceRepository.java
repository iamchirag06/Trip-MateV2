package com.learn.tripmatev2.repository;


import com.learn.tripmatev2.model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
}
