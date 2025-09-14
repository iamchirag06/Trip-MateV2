package com.learn.tripmatev2.service;

import com.learn.tripmatev2.model.User;
import com.learn.tripmatev2.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // Get all users
    public List<User> getAll() {
        return repository.findAll();
    }

    // Get user by ID
    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    // Save new or update existing user
    public User save(User user) {
        return repository.save(user);
    }

    // Delete user by ID
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
