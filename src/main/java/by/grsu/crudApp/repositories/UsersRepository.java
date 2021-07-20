package by.grsu.crudApp.repositories;

import by.grsu.crudApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User,Long> {
    User findOneByUserName(String name);
    User findOneById(Long id);
    User findByUserName(String username);
}
