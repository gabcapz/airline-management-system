package com.airline.management.controller;

import com.airline.management.repository.EmployeeRepository;
import com.airline.management.repository.DepartmentRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {
    private final EmployeeRepository empRepo;
    private final DepartmentRepository deptRepo;
    public DashboardController(EmployeeRepository empRepo, DepartmentRepository deptRepo){this.empRepo=empRepo;this.deptRepo=deptRepo;}
    @GetMapping("/summary")
    public Map<String,Object> summary(){
        return Map.of(
            "totalEmployees", empRepo.count(),
            "totalDepartments", deptRepo.count()
        );
    }
}
