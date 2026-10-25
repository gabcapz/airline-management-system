-- Airline Management System - MySQL Schema
-- Database: airline_management

DROP DATABASE IF EXISTS airline_management;
CREATE DATABASE airline_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE airline_management;

-- Department
CREATE TABLE department (
    department_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100),
    UNIQUE KEY uq_department_name (name)
);

-- Employee (base)
CREATE TABLE employee (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE,
    salary DECIMAL(12,2),
    phone_number VARCHAR(20),
    degree VARCHAR(100),
    department_id INT,
    employee_type VARCHAR(30) NOT NULL DEFAULT 'GENERAL',
    CONSTRAINT fk_employee_department FOREIGN KEY (department_id) REFERENCES department(department_id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE INDEX idx_employee_department ON employee(department_id);
CREATE INDEX idx_employee_name ON employee(last_name, first_name);

-- Engineer subtype
CREATE TABLE engineer (
    employee_id INT PRIMARY KEY,
    experience INT,
    engineer_type VARCHAR(30) NOT NULL,
    CONSTRAINT fk_engineer_employee FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
);

CREATE TABLE aircraft_engineer (
    employee_id INT PRIMARY KEY,
    license_number VARCHAR(50),
    CONSTRAINT fk_ae_engineer FOREIGN KEY (employee_id) REFERENCES engineer(employee_id) ON DELETE CASCADE
);

CREATE TABLE flight_engineer (
    employee_id INT PRIMARY KEY,
    flight_hours_logged INT DEFAULT 0,
    CONSTRAINT fk_fe_engineer FOREIGN KEY (employee_id) REFERENCES engineer(employee_id) ON DELETE CASCADE
);

CREATE TABLE system_engineer (
    employee_id INT PRIMARY KEY,
    software_certification VARCHAR(100),
    CONSTRAINT fk_se_engineer FOREIGN KEY (employee_id) REFERENCES engineer(employee_id) ON DELETE CASCADE
);

-- Crew subtype
CREATE TABLE crew (
    employee_id INT PRIMARY KEY,
    start_time TIME,
    end_time TIME,
    CONSTRAINT fk_crew_employee FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
);

-- Pilot subtype
CREATE TABLE pilot (
    employee_id INT PRIMARY KEY,
    `rank` VARCHAR(50),
    flight_hours INT DEFAULT 0,
    license_number VARCHAR(50),
    CONSTRAINT fk_pilot_employee FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
);

-- Airport
CREATE TABLE airport (
    airport_code VARCHAR(10) PRIMARY KEY,
    city VARCHAR(100),
    name VARCHAR(100),
    country VARCHAR(100)
);

-- Aircraft
CREATE TABLE aircraft (
    aircraft_id INT PRIMARY KEY AUTO_INCREMENT,
    model VARCHAR(100) NOT NULL,
    capacity INT,
    status VARCHAR(30) DEFAULT 'ACTIVE'
);

-- Fuel
CREATE TABLE fuel (
    fuel_id INT PRIMARY KEY AUTO_INCREMENT,
    fuel_type VARCHAR(50) NOT NULL,
    cost DECIMAL(10,2)
);

-- Aircraft consumes Fuel (M:N) -> junction
CREATE TABLE aircraft_fuel (
    aircraft_id INT NOT NULL,
    fuel_id INT NOT NULL,
    PRIMARY KEY (aircraft_id, fuel_id),
    CONSTRAINT fk_af_aircraft FOREIGN KEY (aircraft_id) REFERENCES aircraft(aircraft_id) ON DELETE CASCADE,
    CONSTRAINT fk_af_fuel FOREIGN KEY (fuel_id) REFERENCES fuel(fuel_id) ON DELETE CASCADE
);

-- Aircraft components
CREATE TABLE aircraft_component (
    component_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    hours_of_flight INT DEFAULT 0,
    manufacturer VARCHAR(100)
);

-- Aircraft requires components (M:N) -> junction
CREATE TABLE aircraft_component_link (
    aircraft_id INT NOT NULL,
    component_id INT NOT NULL,
    PRIMARY KEY (aircraft_id, component_id),
    CONSTRAINT fk_acl_aircraft FOREIGN KEY (aircraft_id) REFERENCES aircraft(aircraft_id) ON DELETE CASCADE,
    CONSTRAINT fk_acl_component FOREIGN KEY (component_id) REFERENCES aircraft_component(component_id) ON DELETE CASCADE
);

-- Flight route
CREATE TABLE flight_route (
    flight_number VARCHAR(20) PRIMARY KEY,
    distance INT,
    departure_airport VARCHAR(10),
    arrival_airport VARCHAR(10),
    aircraft_id INT,
    pilot_id INT,
    CONSTRAINT fk_fr_dep FOREIGN KEY (departure_airport) REFERENCES airport(airport_code),
    CONSTRAINT fk_fr_arr FOREIGN KEY (arrival_airport) REFERENCES airport(airport_code),
    CONSTRAINT fk_fr_aircraft FOREIGN KEY (aircraft_id) REFERENCES aircraft(aircraft_id),
    CONSTRAINT fk_fr_pilot FOREIGN KEY (pilot_id) REFERENCES pilot(employee_id)
);

-- Maintenance
CREATE TABLE maintenance (
    maintenance_id INT PRIMARY KEY AUTO_INCREMENT,
    component_id INT,
    start_date DATE,
    end_date DATE,
    status VARCHAR(30) DEFAULT 'SCHEDULED',
    type VARCHAR(50),
    CONSTRAINT fk_maint_component FOREIGN KEY (component_id) REFERENCES aircraft_component(component_id)
);

-- Maintenance record
CREATE TABLE maintenance_record (
    record_number INT PRIMARY KEY AUTO_INCREMENT,
    maintenance_id INT,
    cost DECIMAL(12,2),
    labour_cost DECIMAL(12,2),
    next_service_date DATE,
    CONSTRAINT fk_mr_maintenance FOREIGN KEY (maintenance_id) REFERENCES maintenance(maintenance_id) ON DELETE CASCADE
);

-- Passenger
CREATE TABLE passenger (
    passport_number VARCHAR(30) PRIMARY KEY,
    passenger_id INT UNIQUE,
    first_name VARCHAR(50),
    middle_name VARCHAR(50),
    last_name VARCHAR(50),
    date_of_birth DATE
);

-- Reservation
CREATE TABLE reservation (
    reservation_id INT PRIMARY KEY AUTO_INCREMENT,
    flight_number VARCHAR(20),
    passenger_passport VARCHAR(30),
    booking_date DATE,
    seat_number VARCHAR(10),
    payment DECIMAL(12,2),
    CONSTRAINT fk_res_flight FOREIGN KEY (flight_number) REFERENCES flight_route(flight_number),
    CONSTRAINT fk_res_passenger FOREIGN KEY (passenger_passport) REFERENCES passenger(passport_number)
);

-- Baggage
CREATE TABLE baggage (
    baggage_number INT PRIMARY KEY AUTO_INCREMENT,
    passenger_passport VARCHAR(30),
    colour VARCHAR(30),
    weight DECIMAL(6,2),
    CONSTRAINT fk_bag_passenger FOREIGN KEY (passenger_passport) REFERENCES passenger(passport_number)
);
