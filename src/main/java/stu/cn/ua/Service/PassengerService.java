package stu.cn.ua.Service;

import stu.cn.ua.domain.Passenger;
import stu.cn.ua.domain.Ticket;
import stu.cn.ua.persistene.PassengerRepository;
import stu.cn.ua.persistene.TicketRepository;

import java.util.Set;

public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final TicketRepository ticketRepository;
    public PassengerService(PassengerRepository passengerRepository, TicketRepository ticketRepository) {
        this.passengerRepository = passengerRepository;
        this.ticketRepository = ticketRepository;
    }

    public Passenger findPassengerById(Long id){
        return passengerRepository.findPassengerByPassengerId(id);
    }
    public Passenger save(Passenger passenger){
        return passengerRepository.save(passenger);
    }
    public void deleteById(Long id){
    Passenger passenger = passengerRepository.findPassengerByPassengerId(id);
    Set<Ticket> tickets = passenger.getTickets();

    for(Ticket ticket: tickets){
    ticketRepository.deleteByTicketId(ticket.getTicketId());
    }
    passenger.getTickets().clear();
    passengerRepository.deleteById(passenger.getPassengerId());
    }
}
