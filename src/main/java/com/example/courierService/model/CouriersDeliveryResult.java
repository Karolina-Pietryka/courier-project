package com.example.courierService.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CouriersDeliveryResult {

    private String firstName;
    private String lastName;
    private int packagesDelivered;
}
