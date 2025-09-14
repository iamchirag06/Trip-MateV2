package com.learn.tripmatev2.service;

import com.learn.tripmatev2.model.DestinationAttribute;
import com.learn.tripmatev2.repository.DestinationAttributeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DestinationAttributeService {
    private final DestinationAttributeRepository repo;
    public DestinationAttributeService(DestinationAttributeRepository repo) { this.repo = repo; }

    public List<DestinationAttribute> getAll() { return repo.findAll(); }
    public DestinationAttribute getById(Long id) { return repo.findById(id).orElse(null); }
    public DestinationAttribute save(DestinationAttribute attr) { return repo.save(attr); }
    public void delete(Long id) { repo.deleteById(id); }
}
