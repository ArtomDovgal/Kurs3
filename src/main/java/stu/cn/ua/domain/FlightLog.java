package stu.cn.ua.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@Entity
@Table(name = "flight_log")

public class FlightLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="log_id")
    private Long log_id;

    @Column(name = "flight_id")
    private Long flightId;

    @Column(name = "departure_point")
    private String departurePoint;

    @Column(name="arrival_point")
    private String arrivalPoint;

    @Column(name="departure_time")
    private String departureTime;

    @Column(name="arrival_time")
    private String arrivalTime;

    @OneToMany(mappedBy = "flight",fetch = FetchType.EAGER)
    private Set<Ticket> tickets = new HashSet<>();

    @Column(name="number_of_passengers")
    private long numberOfPassengers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="flight_transport",
            joinColumns = {
                    @JoinColumn(name = "flight_id",referencedColumnName = "flight_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name="transport_id",referencedColumnName = "transport_id")
            })
    private Set<Transport> transports=new HashSet<>();

    public FlightLog() {

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

    public Set<Transport> getTransports() {
        return transports;
    }

    public void setTransports(Set<Transport> transports) {
        this.transports = transports;
    }
}