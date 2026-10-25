package com.airline.management.controller;

import com.airline.management.entity.Airport;
import com.airline.management.repository.AirportRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/airports") @CrossOrigin(origins="http://localhost:5173")
public class AirportController {
    private final AirportRepository repo;
    public AirportController(AirportRepository repo){this.repo=repo;}
    @GetMapping public List<Airport> all(){return repo.findAll();}
    @PostMapping public Airport create(@RequestBody Airport a){return repo.save(a);}
}
