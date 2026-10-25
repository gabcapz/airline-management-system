package com.airline.management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "maintenance")
public class Maintenance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maintenanceId;
    private Integer componentId;
    private java.time.LocalDate startDate;
    private java.time.LocalDate endDate;
    private String status = "SCHEDULED";
    private String type;
    public Integer getMaintenanceId(){return maintenanceId;}
    public void setMaintenanceId(Integer v){maintenanceId=v;}
    public Integer getComponentId(){return componentId;}
    public void setComponentId(Integer v){componentId=v;}
    public java.time.LocalDate getStartDate(){return startDate;}
    public void setStartDate(java.time.LocalDate v){startDate=v;}
    public java.time.LocalDate getEndDate(){return endDate;}
    public void setEndDate(java.time.LocalDate v){endDate=v;}
    public String getStatus(){return status;}
    public void setStatus(String v){status=v;}
    public String getType(){return type;}
    public void setType(String v){type=v;}
}
