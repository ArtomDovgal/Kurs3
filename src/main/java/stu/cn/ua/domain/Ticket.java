package stu.cn.ua.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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

  @Column(name = "date_of_departure_on_the_ticket")
  private Date dateOfDepartueOnTheTicket;
  @Column(name = "date_and_time_of_ticket_sale")

  private Date dateAndTimeOfTiketSale;
  @Column(name = "date_and_time_of_ticket_booking")

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
