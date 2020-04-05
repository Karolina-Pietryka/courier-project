package com.example.courierService.repository;

import com.example.courierService.model.UserForwarder;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserForwarderRepository extends CrudRepository<UserForwarder, Integer> {

    Optional<UserForwarder> findByLogin(String login);
}
