package com.robinfood.ordereports_bc_muyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDataDTO {

    private String email;

    private String firstName;

    private Integer id;

    private String lastName;

    private String mobile;

    private Long orderId;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
