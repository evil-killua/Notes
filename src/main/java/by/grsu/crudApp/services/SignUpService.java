package by.grsu.crudApp.services;

import by.grsu.crudApp.Forms.UserForm;
import by.grsu.crudApp.entity.User;

import java.util.List;

public interface SignUpService {
    void signUp(UserForm userForm);
    User getUserByUserName(String username);
    User getUserById(long id);
    void deleteUser(long id);
    void updateUser(User user);
    List<User> getAllUsers();
}
