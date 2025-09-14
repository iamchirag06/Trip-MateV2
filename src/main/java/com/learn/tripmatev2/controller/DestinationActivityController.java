package com.learn.tripmatev2.controller;

import com.learn.tripmatev2.model.DestinationActivity;
import com.learn.tripmatev2.service.DestinationActivityService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/destination-activities")
public class DestinationActivityController {
    private final DestinationActivityService service;
    public DestinationActivityController(DestinationActivityService service) { this.service = service; }

    @GetMapping
    public List<DestinationActivity> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public DestinationActivity getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    public DestinationActivity create(@RequestBody DestinationActivity da) { return service.save(da); }

    @PutMapping("/{id}")
    public DestinationActivity update(@PathVariable Long id, @RequestBody DestinationActivity da) {
//        da.setId(id);
        return service.save(da);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
