package com.learn.tripmatev2.repository;


import com.learn.tripmatev2.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
}

