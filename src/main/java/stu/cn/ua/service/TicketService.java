package stu.cn.ua.service;

import org.springframework.stereotype.Service;
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

    public Ticket save(Ticket ticket){
    Long idPassenger = ticket.getPassenger().getPassengerId();
    Long idFlight = ticket.getFlight().getFlightId();
    //перевірити умову
    if(idPassenger != null && idFlight != null){
        Passenger passenger = passengerRepository.findPassengerByPassengerId(idPassenger);
        Flight flight = flightRepository.findFlightByFlightId(idFlight);

        if(passenger != null && flight != null){
            Transport[] arr = (Transport[]) flight.getTransports().toArray();
            Integer numberOfSeats = Arrays.stream(arr).findFirst().get().getNumberOfSeats();
            if(flight.getNumberOfPassengers() < numberOfSeats) {
                passenger.getTickets().add(ticket);
                passengerRepository.save(passenger);
                flight.getTickets().add(ticket);
                flightRepository.save(flight);
            }

        }else{
            return null;
        }

    }else{
    //можливо треба викидатипомилку
    return  null;
    }
    return ticketRepository.save(ticket);
    }

    public void deleteById(Long id){
        Ticket ticket = ticketRepository.findTicketByTicketId(id);
        Passenger passenger = ticket.getPassenger();
        Flight flight = ticket.getFlight();

        passenger.getTickets().remove(ticket);
        flight.getTickets().remove(ticket);

        ticketRepository.deleteByTicketId(id);

    }
//переговорити умови редагування( типу чи можна редагувати поле рейс)
    public Ticket update(Ticket ticket){

        return ticketRepository.save(ticket);
    }

    public Set<Ticket> findAll(){
        return ticketRepository.findAll();
    }
}
