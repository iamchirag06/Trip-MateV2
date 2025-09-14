package com.learn.tripmatev2.service;


import com.learn.tripmatev2.model.BudgetRange;
import com.learn.tripmatev2.repository.BudgetRangeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BudgetRangeService {
    private final BudgetRangeRepository repo;
    public BudgetRangeService(BudgetRangeRepository repo) { this.repo = repo; }

    public List<BudgetRange> getAll() { return repo.findAll(); }
    public BudgetRange getById(Long id) { return repo.findById(id).orElse(null); }
    public BudgetRange save(BudgetRange br) { return repo.save(br); }
    public void delete(Long id) { repo.deleteById(id); }
}

