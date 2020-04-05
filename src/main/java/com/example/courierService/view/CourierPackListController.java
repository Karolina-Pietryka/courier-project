package com.example.courierService.view;

import com.example.courierService.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CourierPackListController {

    @Autowired
    private PackService packService;

    @GetMapping("courierPackList")
    public ModelAndView courierPackListView(HttpServletRequest request, Model model) {
        packList(request, model);
        return new ModelAndView("courierPackList");
    }

    @PostMapping("courierPackList")
    public String setDeliveryDateToPackPost(Integer packId) {
        setDeliveryDateToPack(packId);
        return "redirect:courierPackList";
    }

    private void packList(HttpServletRequest request, Model model) {
        model.addAttribute("packList", packService.courierPackList(request));
    }

    private void setDeliveryDateToPack(Integer packId) {
        if (packId != null) packService.setDeliveryDateToPack(packId);
    }
}
