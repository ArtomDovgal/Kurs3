package stu.cn.ua.persistence;

import org.springframework.data.repository.CrudRepository;
import stu.cn.ua.domain.Ticket;

import java.util.Set;

public interface TicketRepository extends CrudRepository<Ticket,Long> {

    Ticket findTicketByTicketId(Long id);

    void deleteByTicketId(Long id);

    Set<Ticket> findAll();

   // Set<Ticket>  findAllByTicketCategoryIsCommon();

}
