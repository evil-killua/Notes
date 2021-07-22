package by.grsu.crudApp.controllers;

import by.grsu.crudApp.dao.RoleDAO;
import by.grsu.crudApp.entity.User;
import by.grsu.crudApp.services.SignUpService;
import by.grsu.crudApp.services.UserRoleService;
import by.grsu.crudApp.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    private UserRoleService userRoleService;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private SignUpService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = {"/", "/welcome"})
    public String welcomePage(Model model) {

        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        logger.info("This is welcome page");

        return "userPage/welcomePage";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {

        User user = userService.getUserById(id);

        List<Long> listId = roleDAO.getId(id);


        for (int i = 0; i < listId.size(); i++) {
            userRoleService.deleteUserRoleById(listId.get(i));
        }

        userService.deleteUser(user.getId());
        logger.info("delete user");

        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "userPage/update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, User user, Model model) {

        if (!user.getUserName().isEmpty()) {
            userService.updateUser(user);

            logger.info("update user");

            return "redirect:/admin";
        } else {
            model.addAttribute("error", "note text is empty");
            logger.error("note text is empty");

            return "userPage/update-user";
        }

    }

    @GetMapping("/user")
    public String userPage(Principal principal, Model model) {

        org.springframework.security.core.userdetails.User registeredUser = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(registeredUser);

        model.addAttribute("userInfo", userInfo);
        logger.info("user page");

        return "userPage/userPage";
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model, Principal principal) {

        org.springframework.security.core.userdetails.User registeredUser = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(registeredUser);

        model.addAttribute("userInfo", userInfo);

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        logger.info("admin page");

        return "userPage/adminPage";
    }

    @GetMapping(value = "/userInfo")
    public String userInfo(Model model, Principal principal) {

        org.springframework.security.core.userdetails.User loginedUser = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("username", principal.getName());

        logger.info("user info page");

        return "userPage/userInfoPage";
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

        return "userPage/403Page";
    }

}
