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
  private String ticketCategory;
  private Date dateOfDepartueOnTheTicket;
  private Date dateAndTimeOfTiketSale;


  @ManyToOne
  @JoinColumn(name = "flightId")
  private Flight flight;

  private Date dateAndTimeOfTicketBooking;

  private long price;

  @ManyToOne
  @JoinColumn(name = "passengerId")
  private Passenger passenger;


  public Ticket() {

  }
}
