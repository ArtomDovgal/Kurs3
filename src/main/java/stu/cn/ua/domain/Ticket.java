package stu.cn.ua.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.ToOne;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "ticket")

public class Ticket {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long ticketId;

  @Enumerated(EnumType.STRING)
  private TicketCategory ticketCategory;

  private Date dateOfDepartueOnTheTicket;
  private Date dateAndTimeOfTiketSale;
  private Date dateAndTimeOfTicketBooking;
  private long price;

  @ManyToOne
  @JoinColumn(name = "flightId")
  private Flight flight;

  @ManyToOne
  @JoinColumn(name = "passengerId")

  private Passenger passenger;

  public Ticket() {

  }
}
