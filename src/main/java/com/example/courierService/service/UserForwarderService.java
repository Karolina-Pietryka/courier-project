package com.example.courierService.service;

import com.example.courierService.model.UserForwarder;
import com.example.courierService.repository.UserForwarderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserForwarderService {

    @Autowired
    private UserForwarderRepository userForwarderRepository;

    public UserForwarder findById(int userForwarderId) {
        return userForwarderRepository.findById(userForwarderId).isPresent() ? userForwarderRepository.findById(userForwarderId).get() : null;
    }

    public UserForwarder findByLogin(String login) {
        return userForwarderRepository.findByLogin(login).isPresent() ? userForwarderRepository.findByLogin(login).get() : null;
    }
}
