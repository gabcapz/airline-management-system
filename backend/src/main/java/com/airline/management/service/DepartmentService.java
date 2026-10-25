package com.airline.management.service;

import com.airline.management.dto.DepartmentDTO;
import com.airline.management.entity.Department;
import com.airline.management.repository.DepartmentRepository;
import com.airline.management.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository repo;
    private final EmployeeRepository empRepo;
    public DepartmentService(DepartmentRepository repo, EmployeeRepository empRepo){this.repo=repo;this.empRepo=empRepo;}

    public List<DepartmentDTO> findAll(){
        return repo.findAll().stream().map(d -> { DepartmentDTO dto = new DepartmentDTO(); dto.setDepartmentId(d.getDepartmentId()); dto.setName(d.getName()); dto.setLocation(d.getLocation()); dto.setEmployeeCount((long)empRepo.findByDepartmentDepartmentId(d.getDepartmentId()).size()); return dto; }).collect(Collectors.toList());
    }
    public Department findById(Integer id){ return repo.findById(id).orElseThrow(() -> new RuntimeException("Department not found")); }
    public Department save(Department d){ return repo.save(d); }
    public void delete(Integer id){
        if(!empRepo.findByDepartmentDepartmentId(id).isEmpty()) throw new RuntimeException("Cannot delete department with employees");
        repo.deleteById(id);
    }
}
