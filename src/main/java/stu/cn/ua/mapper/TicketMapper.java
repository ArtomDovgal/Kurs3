package stu.cn.ua.mapper;

import javax.persistence.*;
import stu.cn.ua.domain.Ticket;
import stu.cn.ua.domain.enums.TicketCategory;


public class TicketMapper {
    private Long ticketId;

    @Enumerated(EnumType.STRING)
    private TicketCategory ticketCategory;

    private String dateOfDepartueOnTheTicket;

    private String dateAndTimeOfTiketSale;

    private String dateAndTimeOfTicketBooking;

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

    public String getDateOfDepartueOnTheTicket() {
        return dateOfDepartueOnTheTicket;
    }

    public void setDateOfDepartueOnTheTicket(String dateOfDepartueOnTheTicket) {
        this.dateOfDepartueOnTheTicket = dateOfDepartueOnTheTicket;
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
