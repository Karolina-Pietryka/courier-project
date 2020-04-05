package com.example.courierService.view;

import com.example.courierService.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("login")
    public ModelAndView loginView() {
        return new ModelAndView("login");
    }

    @PostMapping("login")
    public String loginPost(HttpServletRequest request, String login, String password) {
        return "redirect:" + login(request, login, password);
    }

    private String login(HttpServletRequest request, String login, String password) {
        String result = loginService.login(login, password);
        if (!result.isEmpty()) {
            setSessionAttribute(request, login);
            switch (result) {
                case "courier":
                    return "courierPackList";
                case "forwarder":
                    return "forwarderCreateNewPack";
                case "admin":
                    return "adminCreateNewUser";
            }
        }
        return "login";
    }

    private void setSessionAttribute(HttpServletRequest request, String login) {
        request.getSession().setAttribute("login", login);
    }
}
