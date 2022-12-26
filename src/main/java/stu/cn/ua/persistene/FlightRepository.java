package stu.cn.ua.persistene;

import org.springframework.data.repository.CrudRepository;
import stu.cn.ua.domain.Flight;

import java.util.Set;
public interface FlightRepository extends CrudRepository<Flight,Long> {

    Flight findFlightByFlightId(Long id);

    void deleteByFlightId(Long id);

    Set<Flight> findAll();

    Set<Flight> findAllByNumberOfPassengersGreatedThan(Integer price);


}
