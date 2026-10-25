package com.airline.management.repository;

import com.airline.management.entity.FlightRoute;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FlightRouteRepository extends JpaRepository<FlightRoute, String> {}
