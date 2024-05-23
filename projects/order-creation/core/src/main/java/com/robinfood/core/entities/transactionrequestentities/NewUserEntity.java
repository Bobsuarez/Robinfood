package com.robinfood.core.entities.transactionrequestentities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserEntity {

    private String email;

    private Long id;

    private String lastName;

    private String mobile;

    private String name;

    private String phoneCode;

}
