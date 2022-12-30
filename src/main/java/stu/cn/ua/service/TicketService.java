package stu.cn.ua.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stu.cn.ua.domain.Flight;
import stu.cn.ua.domain.Passenger;
import stu.cn.ua.domain.Ticket;
import stu.cn.ua.domain.Transport;
import stu.cn.ua.persistence.FlightRepository;
import stu.cn.ua.persistence.PassengerRepository;
import stu.cn.ua.persistence.TicketRepository;

import java.util.Arrays;
import java.util.Set;


@Service
public class TicketService {
private final TicketRepository ticketRepository;
private final PassengerRepository passengerRepository;
private final FlightRepository flightRepository;
    public TicketService(TicketRepository ticketRepository,
                         PassengerRepository passengerRepository,
                         FlightRepository flightRepository) {
        this.ticketRepository = ticketRepository;
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
    }

    public Ticket findById(Long id){
        return this.ticketRepository.findTicketByTicketId(id);
    }

    public Ticket save(Ticket ticket,Long flightId,Long passengerId){

    if(flightId!=null)
    {
        Flight flight= flightRepository.findFlightByFlightId(flightId);

        if (flight!=null)
        {
            Transport[] arr = (Transport[]) flight.getTransports().toArray();
            Integer numberOfSeats = Arrays.stream(arr).findFirst().get().getNumberOfSeats();
            if(flight.getNumberOfPassengers() < numberOfSeats)
            {
                flight.getTickets().add(ticket);
            flightRepository.save(flight);
        }
            else
                return null;
        }
    }
    else
        return null;
    if(passengerId!=null)
    {
        Passenger passenger= passengerRepository.findPassengerByPassengerId(passengerId);
        if (passenger!=null)
        {
            passenger.getTickets().add(ticket);
            passengerRepository.save(passenger);
        }
        else
            return null;
    }
    else
        return null;
        return ticketRepository.save(ticket);
    }

    @Transactional
    public void deleteByTicketId(Long id){
        Ticket ticket = ticketRepository.findTicketByTicketId(id);
        Passenger passenger = ticket.getPassenger();
        Flight flight = ticket.getFlight();

        passenger.getTickets().remove(ticket);
        flight.getTickets().remove(ticket);

        ticketRepository.deleteByTicketId(id);

    }
    public Ticket update(Ticket ticket){

        return ticketRepository.save(ticket);
    }

    public Set<Ticket> findAll(){
        return ticketRepository.findAll();
    }
}
