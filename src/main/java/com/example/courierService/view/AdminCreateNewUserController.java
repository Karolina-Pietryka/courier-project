package com.example.courierService.view;

import com.example.courierService.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminCreateNewUserController {

    @Autowired
    private UserAdminService userAdminService;

    @GetMapping("adminCreateNewUser")
    public ModelAndView adminCreateNewUserView() {
        return new ModelAndView("adminCreateNewUser");
    }

    @PostMapping("adminCreateNewUser")
    public String adminCreateNewUserPost(String firstName, String lastName, String role) {
        createNewUser(firstName, lastName, role);
        return "redirect:adminCreateNewUser";
    }

    private void createNewUser(String firstName, String lastName, String role) {
        userAdminService.createUser(firstName, lastName, role);
    }
}
