package com.example.courierService.service;

import com.example.courierService.model.UserCourier;
import com.example.courierService.model.UserForwarder;
import com.example.courierService.repository.UserCourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserCourierService {

    @Autowired
    private UserCourierRepository userCourierRepository;

    @Autowired
    private SessionService sessionService;

    public List<UserCourier> courierList(HttpServletRequest httpServletRequest) {
        UserForwarder userForwarder = sessionService.checkUserForwarderSession(httpServletRequest);
        List<UserCourier> userCouriers = new ArrayList<>();
        if (userForwarder != null) {
            userCouriers = userCourierRepository.findAllByUserForwarder(userForwarder);
        }
        return userCouriers;
    }

    public UserCourier findById(int courierId) {
        return userCourierRepository.findById(courierId).isPresent() ? userCourierRepository.findById(courierId).get() : null;
    }

    public UserCourier findByLogin(String login){
        return userCourierRepository.findByLogin(login).isPresent() ? userCourierRepository.findByLogin(login).get() : null;
    }
}
