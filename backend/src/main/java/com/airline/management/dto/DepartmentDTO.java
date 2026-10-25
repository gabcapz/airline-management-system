package com.airline.management.dto;

public class DepartmentDTO {
    private Integer departmentId;
    private String name;
    private String location;
    private Long employeeCount;
    public Integer getDepartmentId(){return departmentId;}
    public void setDepartmentId(Integer v){departmentId=v;}
    public String getName(){return name;}
    public void setName(String v){name=v;}
    public String getLocation(){return location;}
    public void setLocation(String v){location=v;}
    public Long getEmployeeCount(){return employeeCount;}
    public void setEmployeeCount(Long v){employeeCount=v;}
}
