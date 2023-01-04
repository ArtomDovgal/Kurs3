package stu.cn.ua.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stu.cn.ua.domain.Transport;

import java.util.Set;

@Repository
public interface TransportRepository extends CrudRepository<Transport,Long> {
    Transport findTransportByTransportId(Long id);

    void deleteByTransportId(Long id);

    Set<Transport> findAll();

}
