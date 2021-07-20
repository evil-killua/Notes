package by.grsu.crudApp.controllers;

import by.grsu.crudApp.dao.RoleDAO;
import by.grsu.crudApp.entity.User;
import by.grsu.crudApp.repositories.UserRoleRepository;
import by.grsu.crudApp.repositories.UsersRepository;
import by.grsu.crudApp.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.security.Principal;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleDAO roleDAO;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = {"/", "/welcome"})
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        logger.info("This is welcome page");
        return "welcomePage";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = usersRepository.findOneById(id);

        List<Long> listId = roleDAO.getId(id);


        for (int i = 0; i < listId.size(); i++) {
            userRoleRepository.delete(listId.get(i));
        }

        usersRepository.delete(user.getId());
        logger.info("delete user");
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        by.grsu.crudApp.entity.User user = usersRepository.findOneById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, by.grsu.crudApp.entity.User user) {

        User newUser = by.grsu.crudApp.entity.User.builder()
                .userName(user.getUserName())
                .enable(1)
                .password(passwordEncoder.encode(user.getPassword()))
                .id(user.getId())
                .build();

        usersRepository.save(newUser);
        logger.info("update user");

        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String userPage(Principal principal, Model model) {

        org.springframework.security.core.userdetails.User registeredUser = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(registeredUser);

        model.addAttribute("userInfo", userInfo);
        logger.info("user page");

        return "userPage";
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model, Principal principal) {

        org.springframework.security.core.userdetails.User registeredUser = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(registeredUser);

        model.addAttribute("userInfo", userInfo);

        List<by.grsu.crudApp.entity.User> users = usersRepository.findAll();
        model.addAttribute("users", users);
        logger.info("admin page");

        return "adminPage";
    }

    @GetMapping(value = "/userInfo")
    public String userInfo(Model model, Principal principal) {

        org.springframework.security.core.userdetails.User loginedUser = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("username", principal.getName());
        logger.info("user info page");

        return "userInfoPage";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            org.springframework.security.core.userdetails.User loginedUser = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
            logger.warn("access denied");
        }

        return "403Page";
    }

}
