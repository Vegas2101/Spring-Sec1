package spring.springsec1.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import spring.springsec1.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
   public Role findByName(String roleUser);
}