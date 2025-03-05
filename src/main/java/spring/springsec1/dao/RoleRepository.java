package spring.springsec1.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import spring.springsec1.entity.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
   Role findByName(String roleUser);
}