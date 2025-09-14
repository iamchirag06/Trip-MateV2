package com.learn.tripmatev2.controller;

import com.learn.tripmatev2.model.Activity;
import com.learn.tripmatev2.service.ActivityService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    private final ActivityService service;
    public ActivityController(ActivityService service) { this.service = service; }

    @GetMapping
    public List<Activity> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public Activity getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    public Activity create(@RequestBody Activity act) { return service.save(act); }

    @PutMapping("/{id}")
    public Activity update(@PathVariable Long id, @RequestBody Activity act) {
//        act.setId(id);
        return service.save(act);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
