package spring.springsec1.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import spring.springsec1.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByIdGreaterThan(long id);
}