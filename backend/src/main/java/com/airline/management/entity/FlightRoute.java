package com.airline.management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "flight_route")
public class FlightRoute {
    @Id
    private String flightNumber;
    private Integer distance;
    @ManyToOne @JoinColumn(name="departure_airport")
    private Airport departureAirport;
    @ManyToOne @JoinColumn(name="arrival_airport")
    private Airport arrivalAirport;
    @ManyToOne @JoinColumn(name="aircraft_id")
    private Aircraft aircraft;
    public String getFlightNumber(){return flightNumber;}
    public void setFlightNumber(String v){flightNumber=v;}
    public Integer getDistance(){return distance;}
    public void setDistance(Integer v){distance=v;}
    public Airport getDepartureAirport(){return departureAirport;}
    public void setDepartureAirport(Airport v){departureAirport=v;}
    public Airport getArrivalAirport(){return arrivalAirport;}
    public void setArrivalAirport(Airport v){arrivalAirport=v;}
    public Aircraft getAircraft(){return aircraft;}
    public void setAircraft(Aircraft v){aircraft=v;}
}
