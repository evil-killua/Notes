package by.grsu.crudApp.services;

import by.grsu.crudApp.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void deleteUserRoleById(long id) {
        userRoleRepository.delete(id);
    }
}
