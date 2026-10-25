package com.airline.management.service;

import com.airline.management.dto.EmployeeDTO;
import com.airline.management.entity.Employee;
import com.airline.management.entity.Department;
import com.airline.management.repository.EmployeeRepository;
import com.airline.management.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;
    private final DepartmentRepository deptRepo;
    public EmployeeService(EmployeeRepository repo, DepartmentRepository deptRepo){this.repo=repo;this.deptRepo=deptRepo;}

    private EmployeeDTO toDto(Employee e){
        EmployeeDTO d = new EmployeeDTO();
        d.setEmployeeId(e.getEmployeeId());
        d.setFirstName(e.getFirstName());
        d.setMiddleName(e.getMiddleName());
        d.setLastName(e.getLastName());
        d.setDateOfBirth(e.getDateOfBirth());
        d.setSalary(e.getSalary());
        d.setPhoneNumber(e.getPhoneNumber());
        d.setDegree(e.getDegree());
        d.setEmployeeType(e.getEmployeeType());
        if(e.getDepartment()!=null){ d.setDepartmentId(e.getDepartment().getDepartmentId()); d.setDepartmentName(e.getDepartment().getName()); }
        return d;
    }

    public List<EmployeeDTO> findAll(){ return repo.findAll().stream().map(this::toDto).collect(Collectors.toList()); }
    public List<EmployeeDTO> search(String q){ return repo.search(q).stream().map(this::toDto).collect(Collectors.toList()); }
    public EmployeeDTO findById(Integer id){ return toDto(repo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"))); }
    public EmployeeDTO save(EmployeeDTO dto){
        Employee e = new Employee();
        e.setEmployeeId(dto.getEmployeeId());
        e.setFirstName(dto.getFirstName());
        e.setMiddleName(dto.getMiddleName());
        e.setLastName(dto.getLastName());
        e.setDateOfBirth(dto.getDateOfBirth());
        e.setSalary(dto.getSalary());
        e.setPhoneNumber(dto.getPhoneNumber());
        e.setDegree(dto.getDegree());
        e.setEmployeeType(dto.getEmployeeType()!=null?dto.getEmployeeType():"GENERAL");
        if(dto.getDepartmentId()!=null) e.setDepartment(deptRepo.findById(dto.getDepartmentId()).orElse(null));
        return toDto(repo.save(e));
    }
    public void delete(Integer id){ repo.deleteById(id); }
}
