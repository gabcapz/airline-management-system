package com.airline.management.repository;

import com.airline.management.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AirportRepository extends JpaRepository<Airport, String> {}
