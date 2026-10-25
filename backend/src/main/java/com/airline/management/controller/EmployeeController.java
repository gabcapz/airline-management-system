package com.airline.management.controller;

import com.airline.management.dto.EmployeeDTO;
import com.airline.management.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {
    private final EmployeeService service;
    public EmployeeController(EmployeeService service){this.service=service;}
    @GetMapping public List<EmployeeDTO> all(@RequestParam(required=false) String q){
        if(q!=null && !q.isBlank()) return service.search(q);
        return service.findAll();
    }
    @GetMapping("/{id}") public EmployeeDTO one(@PathVariable Integer id){ return service.findById(id); }
    @PostMapping public EmployeeDTO create(@RequestBody EmployeeDTO dto){ return service.save(dto); }
    @PutMapping("/{id}") public EmployeeDTO update(@PathVariable Integer id, @RequestBody EmployeeDTO dto){ dto.setEmployeeId(id); return service.save(dto); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Integer id){ service.delete(id); }
}
