package stu.cn.ua.Service;

import org.springframework.stereotype.Service;
import stu.cn.ua.domain.Flight;
import stu.cn.ua.domain.Ticket;
import stu.cn.ua.domain.Transport;
import stu.cn.ua.persistene.FlightRepository;
import stu.cn.ua.persistene.TicketRepository;
import stu.cn.ua.persistene.TransportRepository;

import java.util.HashSet;
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
        oldFlight.setDepartutePoint(flight.getDepartutePoint());
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
}