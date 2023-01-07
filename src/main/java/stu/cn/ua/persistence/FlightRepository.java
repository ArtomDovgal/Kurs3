package stu.cn.ua.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import stu.cn.ua.domain.Flight;

import java.time.LocalDateTime;
import java.util.Set;
@Repository
public interface FlightRepository extends CrudRepository<Flight,Long> {

    Flight findFlightByFlightId(Long id);

    void deleteByFlightId(Long id);

    Set<Flight> findAll();

    Set<Flight> findAllByNumberOfPassengersGreaterThan(Integer price);

    @Transactional
    @Procedure(name = "delayFlights")
    void delayFlights(Integer numberOfdays);

    Set<Flight> findAllByArrivalTimeBefore(LocalDateTime arrivalTime);

    Set<Flight> findAllByArrivalPoint(String arrivalPoint);

    @Query(value = "SELECT * FROM flight f WHERE :word in(f.arrival_point,f.departute_point)",nativeQuery = true)
    Set<Flight> searchedFlights( @Param("word")String word);
}