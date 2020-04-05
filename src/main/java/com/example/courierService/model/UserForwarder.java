package com.example.courierService.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class UserForwarder extends User {

    public UserForwarder(Integer id, String firstName, String lastName, String login, String password, List<UserCourier> userCourierList, List<Pack> packList) {
        super(firstName, lastName, login, password);
        this.id = id;
        this.userCourierList = userCourierList;
        this.packList = packList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany
    @JoinColumn(name = "user_forwarder_id")
    private List<UserCourier> userCourierList;

    @OneToMany
    @JoinColumn(name = "user_forwarder_id")
    private List<Pack> packList;
}
