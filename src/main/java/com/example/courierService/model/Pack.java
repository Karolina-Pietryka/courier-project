package com.example.courierService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String recipient;
    private String sender;
    private double weight;
    private LocalDate postingDate;
    private LocalDate deliveryDate;
    private String trackingKey;

    @ManyToOne
    private UserCourier userCourier;

    @ManyToOne
    private UserForwarder userForwarder;
}
