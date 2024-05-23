package com.robinfood.customersbc.thirdparties.dtos;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

import static com.robinfood.customersbc.thirdparties.constants.MessageConstants.EXTERNAL_ID_NOT_NULL_MESSAGE;
import static com.robinfood.customersbc.thirdparties.constants.MessageConstants.FIRST_NAME_NOT_NULL_MESSAGE;
import static com.robinfood.customersbc.thirdparties.constants.MessageConstants.LAST_NAME_NOT_NULL_MESSAGE;
import static com.robinfood.customersbc.thirdparties.constants.MessageConstants.PHONE_CODE_NOT_NULL_MESSAGE;
import static com.robinfood.customersbc.thirdparties.constants.MessageConstants.PHONE_NUMBER_NOT_NULL_MESSAGE;
import static com.robinfood.customersbc.thirdparties.constants.MessageConstants.THIRD_PARTY_NOT_NULL_MESSAGE;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -4640859568854649857L;

    @Parameter(hidden = true)
    private Long id;

    @NotNull(message = EXTERNAL_ID_NOT_NULL_MESSAGE)
    private Long externalId;

    @NotBlank(message = FIRST_NAME_NOT_NULL_MESSAGE)
    private String firstName;

    @NotBlank(message = LAST_NAME_NOT_NULL_MESSAGE)
    private String lastName;

    private String email;

    @NotBlank(message = PHONE_CODE_NOT_NULL_MESSAGE)
    private String phoneCode;

    @NotBlank(message = PHONE_NUMBER_NOT_NULL_MESSAGE)
    private String phoneNumber;

    @NotNull(message = THIRD_PARTY_NOT_NULL_MESSAGE)
    @Valid
    private ThirdPartyDTO thirdParty;
}
