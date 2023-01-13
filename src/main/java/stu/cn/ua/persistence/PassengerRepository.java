package stu.cn.ua.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    @Procedure(procedureName = "passengerByFullName")
    Set<Passenger> passengerByFullName(String firstName,String lastName);


    @Query(value = "SELECT * FROM passenger f WHERE :word in(f.phone,f.privileges,f.first_name,f.last_name)",nativeQuery = true)
    Set<Passenger> searchedPassenger( @Param("word")String word);
}