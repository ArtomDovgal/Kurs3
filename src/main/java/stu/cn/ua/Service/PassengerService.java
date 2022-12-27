package stu.cn.ua.Service;

import stu.cn.ua.domain.Passenger;
import stu.cn.ua.persistene.PassengerRepository;

public class PassengerService {
    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public Passenger findPassengerById(Long id){
        return passengerRepository.findPassengerByPassengerId(id);
    }
    public Passenger save(Passenger passenger){
        return passengerRepository.save(passenger);
    }
    public void deleteById(Long id){

    }
}
