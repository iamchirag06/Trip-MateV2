package com.learn.tripmatev2.controller;

import com.learn.tripmatev2.model.BudgetRange;
import com.learn.tripmatev2.service.BudgetRangeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/budgets")
public class BudgetRangeController {
    private final BudgetRangeService service;
    public BudgetRangeController(BudgetRangeService service) { this.service = service; }

    @GetMapping
    public List<BudgetRange> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public BudgetRange getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    public BudgetRange create(@RequestBody BudgetRange br) { return service.save(br); }

    @PutMapping("/{id}")
    public BudgetRange update(@PathVariable Long id, @RequestBody BudgetRange br) {
//        br.setId(id);
        return service.save(br);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
