package stu.cn.ua.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@Entity
@Table(name = "flight")
public class Flight {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="flight_id")
  private Long flightId;

  @Column(name = "departutePoint")
  private String departurePoint;
  @Column(name="arrival_point")
  private String arrivalPoint;

  ///////////////
  @Column(name="departure_time")
  private LocalDateTime departureTime;
  @Column(name="arrival_time")
  private LocalDateTime arrivalTime;
  ///////////////

  @OneToMany(mappedBy = "flight")
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

  public Flight() {

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

  public LocalDateTime getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(LocalDateTime departureTime) {
    this.departureTime = departureTime;
  }

  public LocalDateTime getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(LocalDateTime arrivalTime) {
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