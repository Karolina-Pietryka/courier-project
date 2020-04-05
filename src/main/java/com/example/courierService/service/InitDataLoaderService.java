package com.example.courierService.service;

import com.example.courierService.model.Pack;
import com.example.courierService.model.UserAdmin;
import com.example.courierService.model.UserCourier;
import com.example.courierService.model.UserForwarder;
import com.example.courierService.repository.PackRepository;
import com.example.courierService.repository.UserAdminRepository;
import com.example.courierService.repository.UserCourierRepository;
import com.example.courierService.repository.UserForwarderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitDataLoaderService {

    @Autowired
    private PackRepository packRepository;

    @Autowired
    private UserAdminRepository userAdminRepository;

    @Autowired
    private UserCourierRepository userCourierRepository;

    @Autowired
    private UserForwarderRepository userForwarderRepository;

    @PostConstruct
    public void init() {
        UserAdmin admin = new UserAdmin(null, "admin", "admin", "6000", "6000");
        userAdminRepository.save(admin);

        LocalDate now = LocalDate.now();
        List<Pack> packList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Pack pack = new Pack(null, "pack- " + i, "pack- " + i, 0.0, now, null, null, null, null);
            packList.add(pack);
            packRepository.save(pack);
        }

        List<UserCourier> userCourierList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            UserCourier userCourier = new UserCourier(null, "userCourier- " + i, "userCourier- " + i, "400" + i, "400" + i, new ArrayList<>(), null);
            userCourierList.add(userCourier);
            userCourierRepository.save(userCourier);
        }

        UserForwarder userForwarder = new UserForwarder(null, "userForwarder", "userForwarder", "5000", "5000", userCourierList, packList);
        userForwarderRepository.save(userForwarder);
    }
}
