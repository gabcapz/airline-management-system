package com.airline.management.controller;

import com.airline.management.entity.FlightRoute;
import com.airline.management.repository.FlightRouteRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/flights") @CrossOrigin(origins="http://localhost:5173")
public class FlightController {
    private final FlightRouteRepository repo;
    public FlightController(FlightRouteRepository repo){this.repo=repo;}
    @GetMapping public List<FlightRoute> all(){return repo.findAll();}
    @PostMapping public FlightRoute create(@RequestBody FlightRoute f){return repo.save(f);}
    @PutMapping("/{id}") public FlightRoute update(@PathVariable String id, @RequestBody FlightRoute f){f.setFlightNumber(id);return repo.save(f);}
    @DeleteMapping("/{id}") public void delete(@PathVariable String id){repo.deleteById(id);}
}
