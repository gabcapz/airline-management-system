package com.airline.management.repository;

import com.airline.management.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {}
