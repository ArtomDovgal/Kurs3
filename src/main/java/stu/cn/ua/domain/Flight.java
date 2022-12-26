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

  private String departutePoint;
  private String arrivalPoint;
  
  ///////////////
  private Date departureTime;
  private Date arrivalTime;
  ///////////////

  @OneToMany(mappedBy = "flight")
  private Set<Ticket> tickets = new HashSet<>();

  private long numberOfPassengers;
  private long transportId;

  public Flight() {
  }
}
