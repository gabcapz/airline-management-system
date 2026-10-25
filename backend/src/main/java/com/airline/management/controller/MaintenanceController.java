package com.airline.management.controller;

import com.airline.management.entity.Maintenance;
import com.airline.management.repository.MaintenanceRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/maintenance") @CrossOrigin(origins="http://localhost:5173")
public class MaintenanceController {
    private final MaintenanceRepository repo;
    public MaintenanceController(MaintenanceRepository repo){this.repo=repo;}
    @GetMapping public List<Maintenance> all(){return repo.findAll();}
    @PostMapping public Maintenance create(@RequestBody Maintenance m){return repo.save(m);}
    @PutMapping("/{id}") public Maintenance update(@PathVariable Integer id, @RequestBody Maintenance m){m.setMaintenanceId(id);return repo.save(m);}
    @DeleteMapping("/{id}") public void delete(@PathVariable Integer id){repo.deleteById(id);}
}
