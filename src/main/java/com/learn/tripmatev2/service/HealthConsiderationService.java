package com.learn.tripmatev2.service;


import com.learn.tripmatev2.model.HealthConsideration;
import com.learn.tripmatev2.repository.HealthConsiderationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HealthConsiderationService {
    private final HealthConsiderationRepository repo;
    public HealthConsiderationService(HealthConsiderationRepository repo) { this.repo = repo; }

    public List<HealthConsideration> getAll() { return repo.findAll(); }
    public HealthConsideration getById(Long id) { return repo.findById(id).orElse(null); }
    public HealthConsideration save(HealthConsideration hc) { return repo.save(hc); }
    public void delete(Long id) { repo.deleteById(id); }
}
