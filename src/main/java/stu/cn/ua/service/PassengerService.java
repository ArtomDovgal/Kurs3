package stu.cn.ua.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stu.cn.ua.domain.Passenger;
import stu.cn.ua.domain.Ticket;
import stu.cn.ua.persistence.PassengerRepository;
import stu.cn.ua.persistence.TicketRepository;

import java.util.HashSet;
import java.util.Set;

@Service
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
        Passenger passenger1=new Passenger();
        Long id= passenger.getPassengerId();
        String firstname =passenger.getFirstName();
        String lastname =passenger.getLastName();
        String privileges= passenger.getPrivileges();
        String number= passenger.getPhone();
        Set<Ticket>tickets =passenger.getTickets();

        passenger1.setPassengerId(id);
        passenger1.setFirstName(firstname);
        passenger1.setLastName(lastname);
        passenger1.setTickets(tickets);
        passenger1.setPhone(number);
        passenger1.setPrivileges(privileges);
        return passengerRepository.save(passenger1);
    }

    @Transactional
    public void deleteByPassengerId(Long id){
    Passenger passenger = passengerRepository.findPassengerByPassengerId(id);

    Set<Ticket> tickets = passenger.getTickets();
    if(tickets != null) {
    for (Ticket ticket : tickets) {
        ticketRepository.deleteByTicketId(ticket.getTicketId());
    }
    passenger.getTickets().clear();
}
    passengerRepository.deleteById(passenger.getPassengerId());
    }

    public Set<Passenger> findAll() {
        return passengerRepository.findAll();
    }
}
