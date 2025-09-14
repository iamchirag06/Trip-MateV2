package com.learn.tripmatev2.controller;

import com.learn.tripmatev2.model.HealthCondition;
import com.learn.tripmatev2.service.HealthConditionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/health-conditions")
public class HealthConditionController {
    private final HealthConditionService service;
    public HealthConditionController(HealthConditionService service) { this.service = service; }

    @GetMapping
    public List<HealthCondition> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public HealthCondition getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    public HealthCondition create(@RequestBody HealthCondition hc) { return service.save(hc); }

    @PutMapping("/{id}")
    public HealthCondition update(@PathVariable Long id, @RequestBody HealthCondition hc) {
//        hc.setId(id);
        return service.save(hc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
