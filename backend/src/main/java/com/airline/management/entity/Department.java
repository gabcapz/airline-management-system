package com.airline.management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "department")
public class Department {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departmentId;
    @Column(nullable = false, unique = true)
    private String name;
    private String location;
    public Integer getDepartmentId(){return departmentId;}
    public void setDepartmentId(Integer v){departmentId=v;}
    public String getName(){return name;}
    public void setName(String v){name=v;}
    public String getLocation(){return location;}
    public void setLocation(String v){location=v;}
}
