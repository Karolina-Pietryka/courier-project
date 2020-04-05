package com.example.courierService.repository;

import com.example.courierService.model.UserAdmin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserAdminRepository extends CrudRepository<UserAdmin, Integer> {

    Optional<UserAdmin> findByLogin(String login);
}
