package stu.cn.ua.persistene;

import org.springframework.data.repository.CrudRepository;
import stu.cn.ua.domain.Transport;

import java.util.Set;

public interface TransportRepository extends CrudRepository<Transport,Long> {
    Transport findTransportByTransportId(Long id);

    void deleteByTransportId();

    Set<Transport> findAll();
}
