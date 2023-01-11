package stu.cn.ua.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stu.cn.ua.domain.*;
import stu.cn.ua.domain.enums.TicketCategory;
import stu.cn.ua.persistence.FlightRepository;
import stu.cn.ua.persistence.PassengerRepository;
import stu.cn.ua.persistence.TicketRepository;

import java.util.*;


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
                // Transport[] arr = (Transport[]) flight.getTransports().toArray();
                //  Optional<Transport> first = flight.getTransports().stream().findFirst();
                // Integer numberOfSeats = Arrays.stream(arr).findFirst().get().getNumberOfSeats();
                // Transport transport= first.get();


//            Iterator iter = flight.getTransports().iterator();
//            Transport transport1 = (Transport) iter.next();
//            Integer numberOfSeats = transport1.getNumberOfSeats();
//
//            if(flight.getNumberOfPassengers() < numberOfSeats)
//            {
//                flight.getTickets().add(ticket);
//            flightRepository.save(flight);
//            ticket.setFlight(flight);
//        }
//            else
//                return null;
                flight.getTickets().add(ticket);
                flightRepository.save(flight);
                ticket.setFlight(flight);
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
                ticket.setPassenger(passenger);
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


//    //на головну тікетів, теж вивести формочку
    public Map<TicketCategory,Integer> countAmountOfTicketsOfEveryCategory()
    {
        Integer usual = 0,luxury=0,business=0;
        Map<TicketCategory,Integer> result = new HashMap<>();
        TicketCategory[] ticketCategories=TicketCategory.values();
        Set<Ticket> tickets= ticketRepository.findAll();
        List<Ticket> ticketArray=new ArrayList<>(tickets.stream().toList());
        for(int i =0;i<ticketArray.size();i++)
        {
            for(int j =0;j<ticketCategories.length;j++)
                switch (ticketCategories[j]) {
                    case USUAL:
                        if(ticketArray.get(i).getTicketCategory()==ticketCategories[j])
                        {
                            usual++;
                        }
                        break;
                    case LUXURY:
                    {
                        if(ticketArray.get(i).getTicketCategory()==ticketCategories[j])
                        {
                            luxury++;
                        }
                    break;
                    }
                    case BUSINESS:
                    {
                        if(ticketArray.get(i).getTicketCategory()==ticketCategories[j])
                        {
                            business++;
                        }
                    break;
                    }
                }
        }
        Integer[] counts={usual,luxury,business};
    for(int i =0;i<ticketCategories.length;i++)
    {
        result.put(ticketCategories[i],counts[i]);
    }
        return result;
    }


    public int countRevenueByCity(String city)
    {
        int counter = 0;
        Set<Ticket> tickets=ticketRepository.findAll();
        for(Ticket ticket:tickets)
            if(ticket.getFlight().getArrivalPoint().equals(city))
                counter+=ticket.getPrice();
        return counter;
    }
    public Set<String> getAllCities()
    {
        Set<String> cities= new HashSet<>();
        for (Ticket ticket:ticketRepository.findAll()) {
            cities.add(ticket.getFlight().getArrivalPoint());
        }
        return cities;
    }
    public Set<Ticket> findAllByArrivalPoint(String city)
    {
        Set<Ticket> ticketsToPassedCity=new HashSet<>();
        for(Ticket ticket:ticketRepository.findAll()) {
            if (ticket.getFlight().getArrivalPoint().equals(city))
                ticketsToPassedCity.add(ticket);
        }
        return ticketsToPassedCity;
    }
    @Transactional
    public int revenueByCity(String city)
    {
        return ticketRepository.revenueByCity(city);
    }

    public Set<Ticket> searchTickets(String word){
        return ticketRepository.searchedTickets(word);
    }
}
