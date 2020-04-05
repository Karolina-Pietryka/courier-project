package com.example.courierService.view;

import com.example.courierService.model.UserCourier;
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
public class ForwarderCourierDetailsController {

    @Autowired
    private UserCourierService userCourierService;

    @Autowired
    private PackService packService;

    @GetMapping("forwarderCourierDetails")
    public ModelAndView forwarderCourierDetailsView(HttpServletRequest request, Integer courierId, Model model) {
        courierDetails(courierId, model);
        courierList(request, model);
        return new ModelAndView("forwarderCourierDetails");
    }

    @PostMapping("forwarderCourierDetailsChange")
    public String forwarderCourierDetailsChangePost(HttpServletRequest request, Integer oldCourierId, Integer newCourierId, Integer packId, Model model) {
        changeCourier(newCourierId, packId);
        courierDetails(oldCourierId, model);
        courierList(request, model);
        return "forwarderCourierDetails";
    }

    private void courierDetails(Integer courierId, Model model) {
        if (checkId(courierId)) {
            UserCourier userCourier = userCourierService.findById(courierId);
            if (userCourier != null)
                model.addAttribute("courier", userCourier);
        }
    }

    private boolean checkId(Integer courierId) {
        return courierId != null;
    }

    private void courierList(HttpServletRequest request, Model model) {
        model.addAttribute("courierList", userCourierService.courierList(request));
    }

    private void changeCourier(Integer newCourierId, Integer packId) {
        if (newCourierId != null && packId != null) {
            packService.changeCourier(newCourierId, packId);
        }
    }
}
