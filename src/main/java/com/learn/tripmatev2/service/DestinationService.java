package com.learn.tripmatev2.service;

import com.learn.tripmatev2.model.Destination;
import com.learn.tripmatev2.repository.DestinationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DestinationService {
    private final DestinationRepository repo;
    public DestinationService(DestinationRepository repo) { this.repo = repo; }

    public List<Destination> getAll() { return repo.findAll(); }
    public Destination getById(Long id) { return repo.findById(id).orElse(null); }
    public Destination save(Destination destination) { return repo.save(destination); }
    public void delete(Long id) { repo.deleteById(id); }
}

