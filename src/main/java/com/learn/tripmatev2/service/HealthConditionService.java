package com.learn.tripmatev2.service;


import com.learn.tripmatev2.model.HealthCondition;
import com.learn.tripmatev2.repository.HealthConditionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HealthConditionService {
    private final HealthConditionRepository repo;
    public HealthConditionService(HealthConditionRepository repo) { this.repo = repo; }

    public List<HealthCondition> getAll() { return repo.findAll(); }
    public HealthCondition getById(Long id) { return repo.findById(id).orElse(null); }
    public HealthCondition save(HealthCondition hc) { return repo.save(hc); }
    public void delete(Long id) { repo.deleteById(id); }
}
