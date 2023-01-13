package stu.cn.ua.persistence;

import org.springframework.data.repository.CrudRepository;
import stu.cn.ua.domain.Role;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findRoleById(Long id);
}
