package com.airline.management.controller;

import com.airline.management.entity.Aircraft;
import com.airline.management.repository.AircraftRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/aircraft") @CrossOrigin(origins="http://localhost:5173")
public class AircraftController {
    private final AircraftRepository repo;
    public AircraftController(AircraftRepository repo){this.repo=repo;}
    @GetMapping public List<Aircraft> all(){return repo.findAll();}
    @PostMapping public Aircraft create(@RequestBody Aircraft a){return repo.save(a);}
    @PutMapping("/{id}") public Aircraft update(@PathVariable Integer id, @RequestBody Aircraft a){a.setAircraftId(id);return repo.save(a);}
    @DeleteMapping("/{id}") public void delete(@PathVariable Integer id){repo.deleteById(id);}
}
