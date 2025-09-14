package com.learn.tripmatev2.service;


import com.learn.tripmatev2.model.Activity;
import com.learn.tripmatev2.repository.ActivityRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActivityService {
    private final ActivityRepository repo;
    public ActivityService(ActivityRepository repo) { this.repo = repo; }

    public List<Activity> getAll() { return repo.findAll(); }
    public Activity getById(Long id) { return repo.findById(id).orElse(null); }
    public Activity save(Activity act) { return repo.save(act); }
    public void delete(Long id) { repo.deleteById(id); }
}
