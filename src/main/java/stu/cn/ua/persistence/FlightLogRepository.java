package stu.cn.ua.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stu.cn.ua.domain.FlightLog;

import java.util.Set;

@Repository
public interface FlightLogRepository extends CrudRepository<FlightLog,Long> {
    Set<FlightLog> findAll();
}
