package stu.cn.ua.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "flight")
public class Flight {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long flightId;

  @Column(name = "departutePoint")
  private String departurePoint;

  private String arrivalPoint;

  ///////////////
  private Date departureTime;
  private Date arrivalTime;
  ///////////////

  @OneToMany(mappedBy = "flight")
  private Set<Ticket> tickets = new HashSet<>();

  private long numberOfPassengers;

  @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  @JoinTable(name="flight_transport",joinColumns = {
          @JoinColumn(name = "flight_id",referencedColumnName = "flightId")
  },
          inverseJoinColumns = {
                  @JoinColumn(name="transport_id",referencedColumnName = "transportId")
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

  public Date getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(Date departureTime) {
    this.departureTime = departureTime;
  }

  public Date getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(Date arrivalTime) {
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