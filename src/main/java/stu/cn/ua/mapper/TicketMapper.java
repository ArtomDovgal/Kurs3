package stu.cn.ua.mapper;

import javax.persistence.*;
import stu.cn.ua.domain.Ticket;
import stu.cn.ua.domain.enums.TicketCategory;

import java.time.LocalDateTime;

public class TicketMapper {
    private Long ticketId;

    @Enumerated(EnumType.STRING)
    private TicketCategory ticketCategory;

    private LocalDateTime dateOfDepartueOnTheTicket;

    private LocalDateTime dateAndTimeOfTiketSale;

    private LocalDateTime dateAndTimeOfTicketBooking;

    private long price;

    private Long flight_id;

    private Long passenger_id;

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

    public LocalDateTime getDateOfDepartueOnTheTicket() {
        return dateOfDepartueOnTheTicket;
    }

    public void setDateOfDepartueOnTheTicket(LocalDateTime dateOfDepartueOnTheTicket) {
        this.dateOfDepartueOnTheTicket = dateOfDepartueOnTheTicket;
    }

    public LocalDateTime getDateAndTimeOfTiketSale() {
        return dateAndTimeOfTiketSale;
    }

    public void setDateAndTimeOfTiketSale(LocalDateTime dateAndTimeOfTiketSale) {
        this.dateAndTimeOfTiketSale = dateAndTimeOfTiketSale;
    }

    public LocalDateTime getDateAndTimeOfTicketBooking() {
        return dateAndTimeOfTicketBooking;
    }

    public void setDateAndTimeOfTicketBooking(LocalDateTime dateAndTimeOfTicketBooking) {
        this.dateAndTimeOfTicketBooking = dateAndTimeOfTicketBooking;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Long getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Long flight_id) {
        this.flight_id = flight_id;
    }

    public Long getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(Long passenger_id) {
        this.passenger_id = passenger_id;
    }
    public TicketMapper(){}

    public TicketMapper(Ticket ticket) {
        this.ticketId = ticket.getTicketId();
        this.passenger_id = ticket.getPassenger().getPassengerId();
        this.ticketCategory = ticket.getTicketCategory();
        this.dateAndTimeOfTicketBooking = ticket.getDateAndTimeOfTicketBooking();
        this.dateAndTimeOfTiketSale = ticket.getDateAndTimeOfTiketSale();
        this.dateOfDepartueOnTheTicket = ticket.getDateOfDepartureOnTheTicket();
    }
}
