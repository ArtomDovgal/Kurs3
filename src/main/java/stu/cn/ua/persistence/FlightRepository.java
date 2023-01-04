package stu.cn.ua.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stu.cn.ua.domain.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
@Repository
public interface FlightRepository extends CrudRepository<Flight,Long> {

    Flight findFlightByFlightId(Long id);

    void deleteByFlightId(Long id);

    Set<Flight> findAll();

    Set<Flight> findAllByNumberOfPassengersGreaterThan(Integer price);

    Set<Flight> findAllByArrivalPoint(String arrivalPoint);

    Set<Flight> findAllByArrivalTimeAfter(LocalDateTime arrivalTime);

    Set<Flight> findAllByDepartureTimeAfter(LocalDateTime arrivalTime);
}
