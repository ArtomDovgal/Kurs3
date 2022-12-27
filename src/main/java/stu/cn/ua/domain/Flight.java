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
          @JoinColumn(name="transport_id",referencedColumnName = "flightId")
  })
  private Set<Transport> transports=new HashSet<>();

  public Flight() {
  }

}
