package com.learn.tripmatev2.controller;

import com.learn.tripmatev2.model.UserPreference;
import com.learn.tripmatev2.service.UserPreferenceService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/preferences")
public class UserPreferenceController {
    private final UserPreferenceService service;
    public UserPreferenceController(UserPreferenceService service) { this.service = service; }

    @GetMapping
    public List<UserPreference> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public UserPreference getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    public UserPreference create(@RequestBody UserPreference pref) { return service.save(pref); }

    @PutMapping("/{id}")
    public UserPreference update(@PathVariable Long id, @RequestBody UserPreference pref) {
//        pref.setId(id);
        return service.save(pref);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
