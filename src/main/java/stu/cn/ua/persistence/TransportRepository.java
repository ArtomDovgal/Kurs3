package stu.cn.ua.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import stu.cn.ua.domain.Ticket;
import stu.cn.ua.domain.Transport;

import java.util.Set;

@Repository
public interface TransportRepository extends CrudRepository<Transport,Long> {
    Transport findTransportByTransportId(Long id);

    void deleteByTransportId(Long id);

    Set<Transport> findAll();
    @Transactional
    @Procedure(procedureName = "numberSeatsAllTransports")
    int sumAllSeats();

//    @Query(value = "SELECT * FROM transport t WHERE :word in(t.ticket_category,t.price)",nativeQuery = true)
//    Set<Ticket> searchedTickets(@Param("word")String word);
}
