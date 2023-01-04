package stu.cn.ua.service;

import org.springframework.stereotype.Service;
import stu.cn.ua.domain.Flight;
import stu.cn.ua.domain.Ticket;
import stu.cn.ua.domain.Transport;
import stu.cn.ua.persistence.FlightRepository;
import stu.cn.ua.persistence.TicketRepository;
import stu.cn.ua.persistence.TransportRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final TransportRepository transportRepository;
    private final TicketRepository ticketRepository;


    public FlightService(FlightRepository flightRepository, TransportRepository transportRepository,
                         TicketRepository ticketRepository) {
        this.flightRepository = flightRepository;
        this.transportRepository = transportRepository;
        this.ticketRepository = ticketRepository;
    }

    public Flight findFlightById(Long id) {
        return this.flightRepository.findFlightByFlightId(id);
    }

    public Flight save(Flight flight) {

        return flightRepository.save(flight);
    }

    public void deleteById(Long id) {
        Flight flight = flightRepository.findFlightByFlightId(id);
        for(Ticket ticket : flight.getTickets()){
          Ticket chosenTicket = ticketRepository.findTicketByTicketId(ticket.getTicketId());
          chosenTicket.setFlight(null);
          ticketRepository.deleteById(chosenTicket.getTicketId());
        }
        flight.getTickets().clear();

        flight.getTransports().clear();
        flightRepository.save(flight);
        flightRepository.deleteById(id);
    }

    public Flight update(Flight flight) {

        Set<Ticket> tickets = new HashSet<>();
        Set<Transport> transports = new HashSet<>();
        Flight oldFlight = flightRepository.findFlightByFlightId(flight.getFlightId());

        //ТОЧНО ПЕРЕРОБИТЬ
        for (Ticket ticket : oldFlight.getTickets()) {
            tickets.add(ticket);
        }
        for (Transport transport : oldFlight.getTransports()) {
            transports.add(transport);
        }
        oldFlight.setArrivalPoint(flight.getArrivalPoint());
        oldFlight.setArrivalTime(flight.getArrivalTime());
        oldFlight.setDepartureTime(flight.getDepartureTime());
        oldFlight.setDeparturePoint(flight.getDeparturePoint());
        oldFlight.setTransports(flight.getTransports());
        oldFlight.setTickets(flight.getTickets());
        return flightRepository.save(oldFlight);
    }

    public void addTransport(Long id, Transport transport) {
        Flight flight = flightRepository.findFlightByFlightId(id);
        if (transport != null) {
            if (transportRepository.findTransportByTransportId
                    (transport.getTransportId()) != null) {
                flight.getTransports().add(transport);
            }
            flightRepository.save(flight);
        }
    }
    public Set<Flight> getAll () {
        return flightRepository.findAll();
    }

    public Set<Flight> findFlightByArrivalPoint(String arrivalPoint)
    {
       return flightRepository.findAllByArrivalPoint(arrivalPoint);
    }

    public Set<Flight> findFlightWithArrivalTimeGraterThen(LocalDateTime arrivalTime)
    {
        return flightRepository.findAllByArrivalTimeAfter(arrivalTime);
    }
    public Set<Flight> findAllByDepartureTimeAfter(LocalDateTime departureTime)
    {
        return flightRepository.findAllByArrivalTimeAfter(departureTime);
    }
}