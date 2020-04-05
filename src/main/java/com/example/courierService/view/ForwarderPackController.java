package com.example.courierService.view;

import com.example.courierService.service.PackService;
import com.example.courierService.service.UserCourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ForwarderPackController {

    @Autowired
    private PackService packService;

    @Autowired
    private UserCourierService userCourierService;

    @GetMapping("forwarderPackList")
    public ModelAndView forwarderPackListView(HttpServletRequest httpServletRequest, Model model) {
        forwarderPackList(httpServletRequest, model);
        forwarderCourierList(httpServletRequest, model);
        return new ModelAndView("forwarderPackList");
    }

    @PostMapping("forwarderPackAssign")
    public String addPackToCourier(Integer courierId, Integer packId) {
        addPack(courierId, packId);
        return "redirect:forwarderPackList";
    }

    @PostMapping("forwarderPackDelete")
    public String deletePackPost(Integer packId) {
        deletePack(packId);
        return "redirect:forwarderPackList";
    }

    private void deletePack(Integer packId) {
        if (packId != null)
            packService.deletePack(packId);
    }

    private void forwarderPackList(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("packList", packService.forwarderPackList(httpServletRequest));
    }

    private void forwarderCourierList(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("courierList", userCourierService.courierList(httpServletRequest));
    }

    private void addPack(Integer courierId, Integer packId) {
        if (courierId != null && packId != null)
            packService.addPackToCourier(courierId, packId);
    }

}
