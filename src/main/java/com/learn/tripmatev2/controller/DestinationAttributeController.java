package com.learn.tripmatev2.controller;


import com.learn.tripmatev2.model.DestinationAttribute;
import com.learn.tripmatev2.service.DestinationAttributeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/destination-attributes")
public class DestinationAttributeController {
    private final DestinationAttributeService service;
    public DestinationAttributeController(DestinationAttributeService service) { this.service = service; }

    @GetMapping
    public List<DestinationAttribute> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public DestinationAttribute getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    public DestinationAttribute create(@RequestBody DestinationAttribute attr) { return service.save(attr); }

    @PutMapping("/{id}")
    public DestinationAttribute update(@PathVariable Long id, @RequestBody DestinationAttribute attr) {
//        attr.setId(id);
        return service.save(attr);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
