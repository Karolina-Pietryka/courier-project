package com.example.courierService.service;

import com.example.courierService.model.UserAdmin;
import com.example.courierService.model.UserCourier;
import com.example.courierService.model.UserForwarder;
import com.example.courierService.repository.UserAdminRepository;
import com.example.courierService.repository.UserCourierRepository;
import com.example.courierService.repository.UserForwarderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class UserAdminService {

    @Autowired
    private UserCourierRepository userCourierRepository;

    @Autowired
    private UserCourierService userCourierService;

    @Autowired
    private UserForwarderRepository userForwarderRepository;

    @Autowired
    private UserForwarderService userForwarderService;

    @Autowired
    private UserAdminRepository userAdminRepository;

    public UserCourier createUserCourier(String firstName, String lastName) {
        UserCourier userCourier = new UserCourier(null, firstName, lastName, generateLogin(), generatePassword(), new ArrayList<>(), null);
        userCourierRepository.save(userCourier);
        return userCourier;
    }

    public UserForwarder createUserForwarder(String firstName, String lastName) {
        UserForwarder userForwarder = new UserForwarder(null, firstName, lastName, generateLogin(), generatePassword(), new ArrayList<>(), new ArrayList<>());
        userForwarderRepository.save(userForwarder);
        return userForwarder;
    }

    public UserAdmin createUserAdmin(String firstName, String lastName) {
        UserAdmin userAdmin = new UserAdmin(null, firstName, lastName, generateLogin(), generatePassword());
        userAdminRepository.save(userAdmin);
        return userAdmin;
    }

    private int generateNumber() {
        Random rand = new Random();
        return rand.nextInt(9001) + 1000;
    }

    private String generateLogin() {
        while (true) {
            String login = "" + generateNumber();

            if (userCourierRepository.findByLogin(login).isPresent() || userForwarderRepository.findByLogin(login).isPresent() || userAdminRepository.findByLogin(login).isPresent())
                continue;

            return login;
        }
    }

    private String generatePassword() {
        return "" + generateNumber();
    }

    public UserAdmin findById(int userAdminId) {
        return userAdminRepository.findById(userAdminId).isPresent() ? userAdminRepository.findById(userAdminId).get() : null;
    }

    public void createUser(String firstName, String lastName, String role) {
        switch (role) {
            case "courier":
                createUserCourier(firstName, lastName);
                break;
            case "forwarder":
                createUserForwarder(firstName, lastName);
                break;
            case "admin":
                createUserAdmin(firstName, lastName);
                break;
        }
    }

    public void assignCourierToForwarder(Integer courierId, Integer forwarderId) {
        UserCourier userCourier = userCourierService.findById(courierId);
        UserForwarder userForwarder = userForwarderService.findById(forwarderId);
        if (userCourier != null && userForwarder != null) {
            userForwarder.getUserCourierList().add(userCourier);
            userForwarderRepository.save(userForwarder);
        }
    }

    public UserAdmin findByLogin(String login) {
        return userAdminRepository.findByLogin(login).isPresent() ? userAdminRepository.findByLogin(login).get() : null;
    }
}
