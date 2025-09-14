package com.learn.tripmatev2.service;


import com.learn.tripmatev2.model.RecommendationActivity;
import com.learn.tripmatev2.repository.RecommendationActivityRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecommendationActivityService {
    private final RecommendationActivityRepository repo;
    public RecommendationActivityService(RecommendationActivityRepository repo) { this.repo = repo; }

    public List<RecommendationActivity> getAll() { return repo.findAll(); }
    public RecommendationActivity getById(Long id) { return repo.findById(id).orElse(null); }
    public RecommendationActivity save(RecommendationActivity ra) { return repo.save(ra); }
    public void delete(Long id) { repo.deleteById(id); }
}
