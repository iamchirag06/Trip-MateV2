package com.learn.tripmatev2.service;


import com.learn.tripmatev2.model.UserPreference;
import com.learn.tripmatev2.repository.UserPreferenceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserPreferenceService {
    private final UserPreferenceRepository repo;
    public UserPreferenceService(UserPreferenceRepository repo) { this.repo = repo; }

    public List<UserPreference> getAll() { return repo.findAll(); }
    public UserPreference getById(Long id) { return repo.findById(id).orElse(null); }
    public UserPreference save(UserPreference pref) { return repo.save(pref); }
    public void delete(Long id) { repo.deleteById(id); }
}
