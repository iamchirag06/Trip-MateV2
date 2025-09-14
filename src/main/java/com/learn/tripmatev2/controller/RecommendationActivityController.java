package com.learn.tripmatev2.controller;

import com.learn.tripmatev2.model.RecommendationActivity;
import com.learn.tripmatev2.service.RecommendationActivityService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recommendation-activities")
public class RecommendationActivityController {
    private final RecommendationActivityService service;
    public RecommendationActivityController(RecommendationActivityService service) { this.service = service; }

    @GetMapping
    public List<RecommendationActivity> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public RecommendationActivity getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    public RecommendationActivity create(@RequestBody RecommendationActivity ra) { return service.save(ra); }

    @PutMapping("/{id}")
    public RecommendationActivity update(@PathVariable Long id, @RequestBody RecommendationActivity ra) {
//        ra.setId(id);
        return service.save(ra);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
