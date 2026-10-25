package com.airline.management.repository;

import com.airline.management.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {}
