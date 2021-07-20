package by.grsu.crudApp.repositories;

import by.grsu.crudApp.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

}
