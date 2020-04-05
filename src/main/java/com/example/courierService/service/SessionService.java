package com.example.courierService.service;

import com.example.courierService.model.UserCourier;
import com.example.courierService.model.UserForwarder;
import com.example.courierService.repository.UserCourierRepository;
import com.example.courierService.repository.UserForwarderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class SessionService {

    @Autowired
    private UserForwarderRepository userForwarderRepository;

    @Autowired
    private UserCourierRepository userCourierRepository;

    public UserForwarder checkUserForwarderSession(HttpServletRequest request) {
        String login = (String) request.getSession().getAttribute("login");
        return userForwarderRepository.findByLogin(login).isPresent() ? userForwarderRepository.findByLogin(login).get() : null;
    }

    public UserCourier checkUserCourierSession(HttpServletRequest request) {
        String login = (String) request.getSession().getAttribute("login");
        return userCourierRepository.findByLogin(login).isPresent() ? userCourierRepository.findByLogin(login).get() : null;
    }
}
