package com.learn.tripmatev2.controller;

import com.learn.tripmatev2.model.Destination;
import com.learn.tripmatev2.service.DestinationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {
    private final DestinationService service;
    public DestinationController(DestinationService service) { this.service = service; }

    @GetMapping
    public List<Destination> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public Destination getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    public Destination create(@RequestBody Destination dest) { return service.save(dest); }

    @PutMapping("/{id}")
    public Destination update(@PathVariable Long id, @RequestBody Destination dest) {
//        dest.setId(id);
        return service.save(dest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
