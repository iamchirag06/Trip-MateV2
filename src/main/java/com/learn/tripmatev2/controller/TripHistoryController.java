package com.learn.tripmatev2.controller;


import com.learn.tripmatev2.model.TripHistory;
import com.learn.tripmatev2.service.TripHistoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripHistoryController {
    private final TripHistoryService service;
    public TripHistoryController(TripHistoryService service) { this.service = service; }

    @GetMapping
    public List<TripHistory> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public TripHistory getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    public TripHistory create(@RequestBody TripHistory trip) { return service.save(trip); }

    @PutMapping("/{id}")
    public TripHistory update(@PathVariable Long id, @RequestBody TripHistory trip) {
//        trip.setId(id);
        return service.save(trip);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
