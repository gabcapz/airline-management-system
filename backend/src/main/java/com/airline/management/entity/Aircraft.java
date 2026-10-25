package com.airline.management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "aircraft")
public class Aircraft {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aircraftId;
    @Column(nullable=false)
    private String model;
    private Integer capacity;
    private String status = "ACTIVE";
    public Integer getAircraftId(){return aircraftId;}
    public void setAircraftId(Integer v){aircraftId=v;}
    public String getModel(){return model;}
    public void setModel(String v){model=v;}
    public Integer getCapacity(){return capacity;}
    public void setCapacity(Integer v){capacity=v;}
    public String getStatus(){return status;}
    public void setStatus(String v){status=v;}
}
