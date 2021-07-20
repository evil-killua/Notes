package by.grsu.crudApp.services;

import by.grsu.crudApp.Forms.UserForm;
import by.grsu.crudApp.controllers.NoteController;
import by.grsu.crudApp.entity.Role;
import by.grsu.crudApp.entity.User;
import by.grsu.crudApp.entity.UserRole;
import by.grsu.crudApp.error.UserAlreadyExistException;
import by.grsu.crudApp.repositories.RoleRepository;
import by.grsu.crudApp.repositories.UserRoleRepository;
import by.grsu.crudApp.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    private static final Logger logger = LoggerFactory.getLogger(SignUpServiceImpl.class);

    @Override
    public void signUp(UserForm userForm) throws UserAlreadyExistException {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());

        User user = User.builder()
                .userName(userForm.getUserName())
                .password(hashPassword)
                .enable(1)
                .build();

        if (usernameExist(user.getUserName())) {
            throw new UserAlreadyExistException("There is an account with this username: "
                    + userForm.getUserName());
        }

        usersRepository.save(user);

        List<Role> roles = roleRepository.findAll();

        User user1 = usersRepository.findOneByUserName(userForm.getUserName());


        UserRole userRole = UserRole.builder()
                .user(user1)
                .role(roles.get(1))
                .build();

        userRoleRepository.save(userRole);
        logger.info("successful authorization");
    }

    private boolean usernameExist(String username) {
        return usersRepository.findOneByUserName(username) != null;
    }
}
