package com.robinfood.core.dtos.transactionrequestdto;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserDTO implements Serializable {

    private static final long serialVersionUID = 7502103070349507262L;

    @NotNull
    @Positive
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private String phoneCode;

    @NotBlank
    private String mobile;

    @NotBlank
    @Email
    private String email;
}
