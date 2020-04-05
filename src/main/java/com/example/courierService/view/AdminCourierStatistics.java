package com.example.courierService.view;

import com.example.courierService.service.AdminCourierStatisticsService;
import com.example.courierService.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminCourierStatistics {

    @Autowired
    private AdminCourierStatisticsService adminCourierStatisticsService;

    @Autowired
    private PdfService pdfService;

    @GetMapping("adminCourierStatistics")
    public ModelAndView adminCourierStatisticsView(Model model) {
        setModelCourierResult(model);
        return new ModelAndView("adminCourierStatistics");
    }

    @PostMapping("adminCourierStatisticsGeneratePdf")
    public String generatePdf() {
        pdfService.generatePdf(adminCourierStatisticsService.courierResult());
        return "redirect:adminCourierStatistics";
    }

    private void setModelCourierResult(Model model) {
        model.addAttribute("courierResult", adminCourierStatisticsService.courierResult());
    }

}
