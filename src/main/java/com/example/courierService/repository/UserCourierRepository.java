package com.example.courierService.repository;

import com.example.courierService.model.UserCourier;
import com.example.courierService.model.UserForwarder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserCourierRepository extends CrudRepository<UserCourier, Integer> {

    List<UserCourier> findAllByUserForwarder(UserForwarder userForwarder);
    Optional<UserCourier> findByLogin(String login);
    List<UserCourier> findAllByUserForwarderIsNull();
}
