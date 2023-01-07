package stu.cn.ua.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stu.cn.ua.domain.Flight;
import stu.cn.ua.domain.Passenger;
import stu.cn.ua.domain.Ticket;
import stu.cn.ua.domain.enums.PassengerPrivileges;
import stu.cn.ua.domain.enums.TicketCategory;
import stu.cn.ua.persistence.PassengerRepository;
import stu.cn.ua.persistence.TicketRepository;

import java.util.*;

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
        String firstname = passenger.getFirstName();
        String lastname = passenger.getLastName();
        PassengerPrivileges privileges= passenger.getPrivileges();
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

    public Map<PassengerPrivileges,Integer> countAmountOfPassengersOfEachPrivileges()
    {
        Integer student=0,pencioner=0,disabled=0,nothing=0;
        Map<PassengerPrivileges,Integer> result = new HashMap<>();
        PassengerPrivileges[] passengerPrivileges=PassengerPrivileges.values();
        Set<Passenger> passengers= passengerRepository.findAll();
        List<Passenger> passengerList=new ArrayList<>(passengers.stream().toList());
        for(int i =0;i<passengerList.size();i++)
        {
            for(int j =0;j<passengerPrivileges.length;j++)
                switch (passengerPrivileges[j]) {
                    case STUDENT:
                        if(passengerList.get(i).getPrivileges()==passengerPrivileges[j])
                        {
                            student++;
                        }
                        break;
                    case PENCIONER:
                        if(passengerList.get(i).getPrivileges()==passengerPrivileges[j])
                        {
                            pencioner++;
                        }
                        break;
                    case DISABLED:
                        if(passengerList.get(i).getPrivileges()==passengerPrivileges[j])
                        {
                            disabled++;
                        }
                        break;
                    case NOTHING:
                        if(passengerList.get(i).getPrivileges()==passengerPrivileges[j])
                        {
                            nothing++;
                        }
                        break;
                }
        }
        Integer[] counts={student,pencioner,disabled,nothing};
        for(int i =0;i<passengerPrivileges.length;i++)
        {
            result.put(passengerPrivileges[i],counts[i]);
        }
        return result;
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

    @Transactional
    public Set<Passenger> findByFirstNameAndLastName(String firstName,String lastName){
        if( firstName != "" && lastName !="" ){
            return passengerRepository.passengerByFullName(firstName, lastName);
        }else
            return new HashSet<>();
}
    public Set<Passenger> searchPassenger(String word){
        return passengerRepository.searchedPassenger(word);
    }
}
