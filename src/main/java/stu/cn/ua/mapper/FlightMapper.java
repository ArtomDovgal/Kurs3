package stu.cn.ua.mapper;

import stu.cn.ua.domain.Flight;
import stu.cn.ua.domain.Ticket;
import stu.cn.ua.domain.Transport;
import stu.cn.ua.service.TransportService;

import java.util.*;

public class FlightMapper {

    private Long flightId;

    private String departurePoint;

    private String arrivalPoint;
    private String departureTime;

    private String arrivalTime;

    private Set<Ticket> tickets = new HashSet<>();

    private long numberOfPassengers;

    private Set<Long> transportIds= new HashSet<>();

    public FlightMapper(){}
    public FlightMapper(Flight flight) {
        this.flightId = flight.getFlightId();
        this.departurePoint = flight.getDeparturePoint();
        this.arrivalPoint = flight.getArrivalPoint();
        this.departureTime = flight.getDepartureTime();
        this.arrivalTime = flight.getArrivalTime();
        this.tickets = flight.getTickets();
        this.numberOfPassengers = flight.getNumberOfPassengers();
        this.transportIds = getTransportIdsFromFlights(flight);
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getArrivalPoint() {
        return arrivalPoint;
    }

    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public long getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(long numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public Set<Long> getTransportIdsFromFlights(Flight flight){
        Set<Long> arrID = new HashSet<>();

        for(Transport transport : flight.getTransports()){
            arrID.add(transport.getTransportId());
        }
        return  arrID;
    }
    public Set<Transport> getTransportsByTransportIds(TransportService transportService){
        Set<Transport> transports = new HashSet<>();
        for(Long transportId : transportIds){
            transports.add(transportService.findTransportById(transportId));
        }
        return transports;
    }

    public Set<Long> getTransportIds() {
        return transportIds;
    }

    public void setTransportIds(Set<Long> transportIds) {
        this.transportIds = transportIds;
    }
}
