package com.example.courierService.view;

import com.example.courierService.service.UserCourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ForwarderCourierListController {

    @Autowired
    private UserCourierService userCourierService;

    @GetMapping("forwarderCourierList")
    public ModelAndView forwarderCourierListView(HttpServletRequest httpServletRequest, Model model) {
        courierList(httpServletRequest, model);
        return new ModelAndView("forwarderCourierList");
    }

    private void courierList(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("courierList", userCourierService.courierList(httpServletRequest));
    }
}
