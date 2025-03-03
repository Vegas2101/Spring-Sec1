package spring.springsec1.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import spring.springsec1.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}