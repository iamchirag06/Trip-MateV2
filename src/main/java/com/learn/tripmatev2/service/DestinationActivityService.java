package com.learn.tripmatev2.service;

import com.learn.tripmatev2.model.DestinationActivity;
import com.learn.tripmatev2.repository.DestinationActivityRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DestinationActivityService {
    private final DestinationActivityRepository repo;
    public DestinationActivityService(DestinationActivityRepository repo) { this.repo = repo; }

    public List<DestinationActivity> getAll() { return repo.findAll(); }
    public DestinationActivity getById(Long id) { return repo.findById(id).orElse(null); }
    public DestinationActivity save(DestinationActivity da) { return repo.save(da); }
    public void delete(Long id) { repo.deleteById(id); }
}
