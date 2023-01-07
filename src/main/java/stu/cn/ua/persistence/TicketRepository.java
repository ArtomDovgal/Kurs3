package stu.cn.ua.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import stu.cn.ua.domain.Passenger;
import stu.cn.ua.domain.Ticket;
import java.util.Set;

@Repository
public interface TicketRepository extends CrudRepository<Ticket,Long> {

    Ticket findTicketByTicketId(Long id);

    void deleteByTicketId(Long id);

    Set<Ticket> findAll();

    @Transactional
    @Procedure(name = "revenueByCity")
    int revenueByCity(String city);

    @Query(value = "SELECT * FROM ticket t WHERE :word in(t.ticket_category,t.price)",nativeQuery = true)
    Set<Ticket> searchedTickets(@Param("word")String word);
}
