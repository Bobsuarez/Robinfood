package com.robinfood.dtos.request.orderbill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class NewUserDTO {

    private String email;

    private String firstName;

    private Long id;

    private String lastName;

    private String mobile;

    private String phoneCode;

}
