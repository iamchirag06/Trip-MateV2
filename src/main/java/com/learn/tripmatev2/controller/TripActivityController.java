package com.learn.tripmatev2.controller;

import com.learn.tripmatev2.model.TripActivity;
import com.learn.tripmatev2.service.TripActivityService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/trip-activities")
public class TripActivityController {
    private final TripActivityService service;
    public TripActivityController(TripActivityService service) { this.service = service; }

    @GetMapping
    public List<TripActivity> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public TripActivity getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    public TripActivity create(@RequestBody TripActivity ta) { return service.save(ta); }

    @PutMapping("/{id}")
    public TripActivity update(@PathVariable Long id, @RequestBody TripActivity ta) {
//        ta.setId(id);
        return service.save(ta);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
