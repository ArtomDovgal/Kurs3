package stu.cn.ua.persistence;

import org.springframework.data.repository.CrudRepository;
import stu.cn.ua.domain.TransportCategory;

import java.util.Set;

public interface TransportCategoryRepository extends CrudRepository<TransportCategory,Long> {

    TransportCategory findTransportCategoryByTransportCategoryId(Long id);

    void deleteById(Long id);

    Set<TransportCategory> findAll();
}
