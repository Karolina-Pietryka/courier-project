package com.example.courierService.repository;

import com.example.courierService.model.Pack;
import com.example.courierService.model.UserCourier;
import com.example.courierService.model.UserForwarder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PackRepository extends CrudRepository<Pack, Integer> {

    List<Pack> findAllByUserForwarderAndUserCourierIsNull(UserForwarder userForwarder);
    List<Pack> findAllByUserCourierAndDeliveryDateIsNull(UserCourier userCourier);
    int countByUserCourierAndDeliveryDateIsNotNull(UserCourier userCourier);
}
