package by.grsu.crudApp.controllers;


import by.grsu.crudApp.Forms.UserForm;
import by.grsu.crudApp.services.SignUpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class LoginController {
    
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SignUpService service;

    @GetMapping
    public String getSignUpPage() {

        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(UserForm userForm, Model model) {
        try {

            service.signUp(userForm);
            logger.info("Successful registration");
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "There is an account with this username:");
            return "signUp";
        }
    }

    @GetMapping(value = "/login")
    public String loginPage(Model model) {

        return "login";
    }

    @GetMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {

        model.addAttribute("title", "Logout");
        logger.info("successful logout");
        return "logoutSuccessfulPage";
    }

}
