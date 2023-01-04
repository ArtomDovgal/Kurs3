package stu.cn.ua.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stu.cn.ua.domain.TransportCategory;

import java.util.Set;

@Repository
public interface TransportCategoryRepository extends CrudRepository<TransportCategory,Long> {

    TransportCategory findTransportCategoryByTransportCategoryId(Long id);

    void deleteById(Long id);

    Set<TransportCategory> findAll();
}
