package com.learn.tripmatev2.controller;

import com.learn.tripmatev2.model.HealthConsideration;
import com.learn.tripmatev2.service.HealthConsiderationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/health-considerations")
public class HealthConsiderationController {
    private final HealthConsiderationService service;
    public HealthConsiderationController(HealthConsiderationService service) { this.service = service; }

    @GetMapping
    public List<HealthConsideration> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public HealthConsideration getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    public HealthConsideration create(@RequestBody HealthConsideration hc) { return service.save(hc); }

    @PutMapping("/{id}")
    public HealthConsideration update(@PathVariable Long id, @RequestBody HealthConsideration hc) {
//        hc.setId(id);
        return service.save(hc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
