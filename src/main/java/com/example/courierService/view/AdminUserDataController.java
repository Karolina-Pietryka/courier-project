package com.example.courierService.view;

import com.example.courierService.service.UserAdminService;
import com.example.courierService.service.UserCourierService;
import com.example.courierService.service.UserForwarderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminUserDataController {

    @Autowired
    private UserCourierService userCourierService;

    @Autowired
    private UserForwarderService userForwarderService;

    @Autowired
    private UserAdminService userAdminService;

    @GetMapping("adminUserData")
    public ModelAndView adminUserDataView(String id, String role, Model model) {
        getUser(id, role, model);
        return new ModelAndView("adminUserData");
    }

    private void getUser(String id, String role, Model model) {
        int userId = Integer.parseInt(id);
        switch (role) {
            case "courier":
                model.addAttribute("user", userCourierService.findById(userId));
                break;
            case "forwarder":
                model.addAttribute("user", userForwarderService.findById(userId));
                break;
            case "admin":
                model.addAttribute("user", userAdminService.findById(userId));
                break;
        }
    }
}
