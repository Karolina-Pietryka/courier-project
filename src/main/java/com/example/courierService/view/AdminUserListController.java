package com.example.courierService.view;

import com.example.courierService.repository.UserAdminRepository;
import com.example.courierService.repository.UserCourierRepository;
import com.example.courierService.repository.UserForwarderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminUserListController {

    @Autowired
    private UserCourierRepository userCourierRepository;

    @Autowired
    private UserForwarderRepository userForwarderRepository;

    @Autowired
    private UserAdminRepository userAdminRepository;

    @GetMapping("adminUserList")
    public ModelAndView adminUserListView(Model model) {
        courierList(model);
        forwarderList(model);
        adminList(model);
        return new ModelAndView("adminUserList");
    }

    private void courierList(Model model) {
        model.addAttribute("courierList", userCourierRepository.findAll());
    }

    private void forwarderList(Model model) {
        model.addAttribute("forwarderList", userForwarderRepository.findAll());
    }

    private void adminList(Model model) {
        model.addAttribute("adminList", userAdminRepository.findAll());
    }
}
