package com.airline.management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "airport")
public class Airport {
    @Id
    private String airportCode;
    private String city;
    private String name;
    private String country;
    public String getAirportCode(){return airportCode;}
    public void setAirportCode(String v){airportCode=v;}
    public String getCity(){return city;}
    public void setCity(String v){city=v;}
    public String getName(){return name;}
    public void setName(String v){name=v;}
    public String getCountry(){return country;}
    public void setCountry(String v){country=v;}
}
