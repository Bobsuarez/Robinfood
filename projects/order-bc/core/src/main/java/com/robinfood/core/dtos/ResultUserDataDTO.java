package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultUserDataDTO {
    private  String firstName;
    private Long id;
    private  String lastName;
    private Long orderId;
}
