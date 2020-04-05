package com.example.courierService.service;

import com.example.courierService.model.UserAdmin;
import com.example.courierService.model.UserCourier;
import com.example.courierService.model.UserForwarder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserCourierService userCourierService;

    @Autowired
    private UserForwarderService userForwarderService;

    @Autowired
    private UserAdminService userAdminService;

    public String login(String login, String password) {
        String role = checkUserCourier(login, password);
        if (!role.isEmpty()) return role;
        role = checkUserForwarder(login, password);
        if (!role.isEmpty()) return role;
        role = checkUserAdmin(login, password);
        if (!role.isEmpty()) return role;
        return role;
    }

    private String checkUserCourier(String login, String password) {
        UserCourier userCourier = userCourierService.findByLogin(login);
        if (userCourier != null)
            return userCourier.getPassword().equals(password) ? "courier" : "";
        return "";
    }

    private String checkUserForwarder(String login, String password) {
        UserForwarder userForwarder = userForwarderService.findByLogin(login);
        if (userForwarder != null)
            return userForwarder.getPassword().equals(password) ? "forwarder" : "";
        return "";
    }

    private String checkUserAdmin(String login, String password) {
        UserAdmin userAdmin = userAdminService.findByLogin(login);
        if (userAdmin != null)
            return userAdmin.getPassword().equals(password) ? "admin" : "";
        return "";
    }
}
