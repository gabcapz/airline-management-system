package com.airline.management.repository;

import com.airline.management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByDepartmentDepartmentId(Integer deptId);
    @Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%',:q,'%')) OR LOWER(e.lastName) LIKE LOWER(CONCAT('%',:q,'%')) OR CAST(e.employeeId AS string) LIKE CONCAT('%',:q,'%')")
    List<Employee> search(String q);
}
