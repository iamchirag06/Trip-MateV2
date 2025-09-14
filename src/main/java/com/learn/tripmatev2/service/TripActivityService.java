package com.learn.tripmatev2.service;

import com.learn.tripmatev2.model.TripActivity;
import com.learn.tripmatev2.repository.TripActivityRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TripActivityService {
    private final TripActivityRepository repo;
    public TripActivityService(TripActivityRepository repo) { this.repo = repo; }

    public List<TripActivity> getAll() { return repo.findAll(); }
    public TripActivity getById(Long id) { return repo.findById(id).orElse(null); }
    public TripActivity save(TripActivity ta) { return repo.save(ta); }
    public void delete(Long id) { repo.deleteById(id); }
}

