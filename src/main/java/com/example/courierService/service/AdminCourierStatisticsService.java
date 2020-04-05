package com.example.courierService.service;

import com.example.courierService.model.CouriersDeliveryResult;
import com.example.courierService.model.UserCourier;
import com.example.courierService.repository.PackRepository;
import com.example.courierService.repository.UserCourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminCourierStatisticsService {

    @Autowired
    private UserCourierRepository userCourierRepository;

    @Autowired
    private PackRepository packRepository;

    public List<CouriersDeliveryResult> courierResult() {
        List<UserCourier> courierList = (List<UserCourier>) userCourierRepository.findAll();
        List<CouriersDeliveryResult> resultList = new ArrayList<>();
        CouriersDeliveryResult temp;

        for (UserCourier userCourier : courierList) {
            temp = new CouriersDeliveryResult(userCourier.getFirstName(), userCourier.getLastName(), packRepository.countByUserCourierAndDeliveryDateIsNotNull(userCourier));
            resultList.add(temp);
        }
        return resultList;
    }
}
