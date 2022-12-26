package stu.cn.ua.persistene;

import org.springframework.data.repository.CrudRepository;
import stu.cn.ua.domain.Flight;
import stu.cn.ua.domain.Passenger;

import java.util.Set;

public interface PassengerRepository extends CrudRepository<Passenger,Long> {

    Passenger findPassengerByPassengerId(Long id);
    void deleteByPassengerId(Long id);
    Set<Passenger> findAll();
    Set<Flight> findAllByFirstName();

}
