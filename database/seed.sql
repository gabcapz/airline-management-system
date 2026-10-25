USE airline_management;
INSERT INTO department (name, location) VALUES ('Flight Operations','Mumbai'),('Human Resources','Delhi'),('Engineering','Bangalore');
INSERT INTO employee (first_name,last_name,salary,phone_number,degree,department_id,employee_type) VALUES
('Raj','Sharma',85000,'9876543210','B.Tech',1,'PILOT'),
('Anita','Verma',60000,'9876543211','MBA',2,'HR'),
('Vikram','Singh',75000,'9876543212','B.E.',3,'ENGINEER');
INSERT INTO aircraft (model,capacity,status) VALUES ('Boeing 737',180,'ACTIVE'),('Airbus A320',150,'ACTIVE');
INSERT INTO airport (airport_code,city,name,country) VALUES ('BOM','Mumbai','Chhatrapati Shivaji','India'),('DEL','Delhi','Indira Gandhi','India');
INSERT INTO maintenance (component_id,start_date,status,type) VALUES (NULL,'2026-10-01','SCHEDULED','Routine');
