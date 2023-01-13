package stu.cn.ua.domain;

import javax.persistence.*;
import stu.cn.ua.domain.enums.TicketCategory;
import stu.cn.ua.mapper.TicketMapper;



@Entity
@Table(name = "ticket")
public class Ticket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ticketId;

  @Enumerated(EnumType.STRING)
  private TicketCategory ticketCategory;

  @Column(name = "date_of_departure_on_the_ticket")
  private String dateOfDepartureOnTheTicket;

  @Column(name = "date_and_time_of_ticket_sale")
  private String dateAndTimeOfTiketSale;

  @Column(name = "date_and_time_of_ticket_booking")
  private String dateAndTimeOfTicketBooking;
  @Column(name="price")
  private Long price;

  @ManyToOne
  @JoinColumn(name = "flightId")
  private Flight flight;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "passenge_id")
  private Passenger passenger;

  public Ticket() {

  }
  public Ticket(TicketMapper ticketMapper){
    this.ticketId = ticketMapper.getTicketId();
    this.ticketCategory = ticketMapper.getTicketCategory();
    this.dateAndTimeOfTicketBooking = ticketMapper.getDateAndTimeOfTicketBooking();
    this.dateAndTimeOfTiketSale = ticketMapper.getDateAndTimeOfTiketSale();
    this.dateOfDepartureOnTheTicket = ticketMapper.getDateOfDepartueOnTheTicket();
    this.price = ticketMapper.getPrice();
  }

  public Long getTicketId() {
    return ticketId;
  }

  public void setTicketId(Long ticketId) {
    this.ticketId = ticketId;
  }

  public TicketCategory getTicketCategory() {
    return ticketCategory;
  }

  public void setTicketCategory(TicketCategory ticketCategory) {
    this.ticketCategory = ticketCategory;
  }

  public String getDateOfDepartureOnTheTicket() {
    return dateOfDepartureOnTheTicket;
  }

  public void setDateOfDepartureOnTheTicket(String dateOfDepartureOnTheTicket) {
    this.dateOfDepartureOnTheTicket = dateOfDepartureOnTheTicket;
  }

  public String getDateAndTimeOfTiketSale() {
    return dateAndTimeOfTiketSale;
  }

  public void setDateAndTimeOfTiketSale(String dateAndTimeOfTiketSale) {
    this.dateAndTimeOfTiketSale = dateAndTimeOfTiketSale;
  }

  public String getDateAndTimeOfTicketBooking() {
    return dateAndTimeOfTicketBooking;
  }

  public void setDateAndTimeOfTicketBooking(String dateAndTimeOfTicketBooking) {
    this.dateAndTimeOfTicketBooking = dateAndTimeOfTicketBooking;
  }

  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }

  public Flight getFlight() {
    return flight;
  }

  public void setFlight(Flight flight) {
    this.flight = flight;
  }

  public Passenger getPassenger() {
    return passenger;
  }

  public void setPassenger(Passenger passenger) {
    this.passenger = passenger;
  }
}