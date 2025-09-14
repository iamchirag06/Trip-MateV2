package com.learn.tripmatev2.service;


import com.learn.tripmatev2.model.TripHistory;
import com.learn.tripmatev2.repository.TripHistoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TripHistoryService {
    private final TripHistoryRepository repo;
    public TripHistoryService(TripHistoryRepository repo) { this.repo = repo; }

    public List<TripHistory> getAll() { return repo.findAll(); }
    public TripHistory getById(Long id) { return repo.findById(id).orElse(null); }
    public TripHistory save(TripHistory trip) { return repo.save(trip); }
    public void delete(Long id) { repo.deleteById(id); }
}
