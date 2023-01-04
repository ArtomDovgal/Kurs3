package stu.cn.ua.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stu.cn.ua.domain.Flight;
import stu.cn.ua.domain.Passenger;

import java.util.Map;
import java.util.Set;
@Repository

public interface PassengerRepository extends CrudRepository<Passenger,Long> {

    Passenger findPassengerByPassengerId(Long id);
    void deleteByPassengerId(Long id);
    Set<Passenger> findAll();
    Set<Passenger> findAllByFirstName(String firstName);
    Passenger save(Passenger passenger);
}