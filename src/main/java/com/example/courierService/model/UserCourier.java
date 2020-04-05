package com.example.courierService.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class UserCourier extends User {

    public UserCourier(Integer id, String firstName, String lastName, String login, String password, List<Pack> packList, UserForwarder userForwarder) {
        super(firstName, lastName, login, password);
        this.id = id;
        this.packList = packList;
        this.userForwarder = userForwarder;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany
    @JoinColumn(name = "user_courier_id")
    private List<Pack> packList;

    @ManyToOne
    private UserForwarder userForwarder;
}
