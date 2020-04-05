package com.example.courierService.view;

import com.example.courierService.model.Pack;
import com.example.courierService.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ForwarderCreateNewPackController {

    @Autowired
    private PackService packService;

    @GetMapping("forwarderCreateNewPack")
    public ModelAndView createNewPackView() {
        return new ModelAndView("forwarderCreateNewPack");
    }

    @PostMapping("forwarderCreateNewPack")
    public ModelAndView createNewPack(HttpServletRequest httpServletRequest, String recipient, String sender, double weight, Model model) {
        createPack(httpServletRequest, recipient, sender, weight, model);
        return new ModelAndView("forwarderCreateNewPack");
    }

    private void correct(Model model) {
        model.addAttribute("message", "Pack create");
        model.addAttribute("messageType", "success");
    }

    private void error(Model model) {
        model.addAttribute("message", "error");
        model.addAttribute("messageType", "danger");
    }

    private void createPack(HttpServletRequest httpServletRequest, String recipient, String sender, double weight, Model model) {
        Pack pack = packService.createNewPack(httpServletRequest, recipient, sender, weight);
        if (pack != null) {
            correct(model);
        } else {
            error(model);
        }
    }
}
