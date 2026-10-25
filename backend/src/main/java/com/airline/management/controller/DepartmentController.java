package com.airline.management.controller;

import com.airline.management.dto.DepartmentDTO;
import com.airline.management.entity.Department;
import com.airline.management.service.DepartmentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "http://localhost:5173")
public class DepartmentController {
    private final DepartmentService service;
    public DepartmentController(DepartmentService service){this.service=service;}
    @GetMapping public List<DepartmentDTO> all(){ return service.findAll(); }
    @GetMapping("/{id}") public Department one(@PathVariable Integer id){ return service.findById(id); }
    @PostMapping public Department create(@RequestBody Department d){ return service.save(d); }
    @PutMapping("/{id}") public Department update(@PathVariable Integer id, @RequestBody Department d){ d.setDepartmentId(id); return service.save(d); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Integer id){ service.delete(id); }
}
