package by.grsu.crudApp.repositories;

import by.grsu.crudApp.entity.Role;
import by.grsu.crudApp.entity.User;
import by.grsu.crudApp.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

}
