package stu.cn.ua.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import stu.cn.ua.domain.Ticket;
import stu.cn.ua.domain.TransportCategory;

import java.util.Set;

@Repository
public interface TransportCategoryRepository extends CrudRepository<TransportCategory,Long> {

    TransportCategory findTransportCategoryByTransportCategoryId(Long id);

    void deleteById(Long id);

    Set<TransportCategory> findAll();
    @Query(value = "SELECT * FROM transport_category t WHERE :word in(t.vechicle_type,t.model)",nativeQuery = true)
    Set<TransportCategory> searchedTransportCategories(@Param("word")String word);

}
