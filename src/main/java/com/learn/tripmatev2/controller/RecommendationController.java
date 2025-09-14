package com.learn.tripmatev2.controller;

import com.learn.tripmatev2.model.Recommendation;
import com.learn.tripmatev2.service.RecommendationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {
    private final RecommendationService service;
    public RecommendationController(RecommendationService service) { this.service = service; }

    @GetMapping
    public List<Recommendation> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public Recommendation getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    public Recommendation create(@RequestBody Recommendation rec) { return service.save(rec); }

    @PutMapping("/{id}")
    public Recommendation update(@PathVariable Long id, @RequestBody Recommendation rec) {
//        rec.setId(id);
        return service.save(rec);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
