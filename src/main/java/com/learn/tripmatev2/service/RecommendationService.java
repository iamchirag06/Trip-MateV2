package com.learn.tripmatev2.service;


import com.learn.tripmatev2.model.Recommendation;
import com.learn.tripmatev2.repository.RecommendationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecommendationService {
    private final RecommendationRepository repo;
    public RecommendationService(RecommendationRepository repo) { this.repo = repo; }

    public List<Recommendation> getAll() { return repo.findAll(); }
    public Recommendation getById(Long id) { return repo.findById(id).orElse(null); }
    public Recommendation save(Recommendation rec) { return repo.save(rec); }
    public void delete(Long id) { repo.deleteById(id); }
}
