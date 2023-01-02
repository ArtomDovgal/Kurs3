package stu.cn.ua.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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
}