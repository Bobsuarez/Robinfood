package com.robinfood.ordereports.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourierDTO implements Serializable {

    private String name;

    private String phone;

    private String photoUrl;

    private String vehiclePlate;
}
