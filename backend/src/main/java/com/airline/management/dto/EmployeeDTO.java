package com.airline.management.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeDTO {
    private Integer employeeId;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private BigDecimal salary;
    private String phoneNumber;
    private String degree;
    private Integer departmentId;
    private String departmentName;
    private String employeeType;

    public Integer getEmployeeId(){return employeeId;}
    public void setEmployeeId(Integer v){employeeId=v;}
    public String getFirstName(){return firstName;}
    public void setFirstName(String v){firstName=v;}
    public String getMiddleName(){return middleName;}
    public void setMiddleName(String v){middleName=v;}
    public String getLastName(){return lastName;}
    public void setLastName(String v){lastName=v;}
    public LocalDate getDateOfBirth(){return dateOfBirth;}
    public void setDateOfBirth(LocalDate v){dateOfBirth=v;}
    public BigDecimal getSalary(){return salary;}
    public void setSalary(BigDecimal v){salary=v;}
    public String getPhoneNumber(){return phoneNumber;}
    public void setPhoneNumber(String v){phoneNumber=v;}
    public String getDegree(){return degree;}
    public void setDegree(String v){degree=v;}
    public Integer getDepartmentId(){return departmentId;}
    public void setDepartmentId(Integer v){departmentId=v;}
    public String getDepartmentName(){return departmentName;}
    public void setDepartmentName(String v){departmentName=v;}
    public String getEmployeeType(){return employeeType;}
    public void setEmployeeType(String v){employeeType=v;}
}
