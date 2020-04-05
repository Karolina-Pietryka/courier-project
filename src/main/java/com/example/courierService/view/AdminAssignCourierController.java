package com.example.courierService.view;

import com.example.courierService.repository.UserCourierRepository;
import com.example.courierService.repository.UserForwarderRepository;
import com.example.courierService.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminAssignCourierController {

    @Autowired
    private UserCourierRepository userCourierRepository;

    @Autowired
    private UserForwarderRepository userForwarderRepository;

    @Autowired
    private UserAdminService userAdminService;

    @GetMapping("adminAssignCourier")
    public ModelAndView adminAssignCourierView(Model model) {
        courierList(model);
        forwarderList(model);
        return new ModelAndView("adminAssignCourier");
    }

    @PostMapping("adminAssignCourier")
    public String adminAssignCourierPost(Integer courierId, Integer forwarderId) {
        assignCourierToForwarder(courierId, forwarderId);
        return "redirect:adminAssignCourier";
    }

    private void courierList(Model model) {
        model.addAttribute("courierList", userCourierRepository.findAllByUserForwarderIsNull());
    }

    private void forwarderList(Model model) {
        model.addAttribute("forwarderList", userForwarderRepository.findAll());
    }

    private void assignCourierToForwarder(Integer courierId, Integer forwarderId){
        if (courierId != null && forwarderId != null)
            userAdminService.assignCourierToForwarder(courierId, forwarderId);
    }

}
