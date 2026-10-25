package com.airline.management.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    @Column(nullable = false)
    private String firstName;
    private String middleName;
    @Column(nullable = false)
    private String lastName;
    private LocalDate dateOfBirth;
    private BigDecimal salary;
    private String phoneNumber;
    private String degree;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @Column(nullable = false)
    private String employeeType = "GENERAL";

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
    public Department getDepartment(){return department;}
    public void setDepartment(Department v){department=v;}
    public String getEmployeeType(){return employeeType;}
    public void setEmployeeType(String v){employeeType=v;}
}
