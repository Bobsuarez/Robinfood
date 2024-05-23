package com.robinfood.customersbc.thirdparties.dtos;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

import static com.robinfood.customersbc.thirdparties.constants.MessageConstants.EMAIL_NOT_NULL_MESSAGE;
import static com.robinfood.customersbc.thirdparties.constants.MessageConstants.FIRST_NAME_NOT_NULL_MESSAGE;
import static com.robinfood.customersbc.thirdparties.constants.MessageConstants.IDENTIFICATION_NUMBER_NOT_NULL_MESSAGE;
import static com.robinfood.customersbc.thirdparties.constants.MessageConstants.IDENTIFICATION_TYPE_ID_GREATER_THAN_ZERO_MESSAGE;
import static com.robinfood.customersbc.thirdparties.constants.MessageConstants.IDENTIFICATION_TYPE_ID_NOT_NULL_MESSAGE;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThirdPartyDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 799717139283777330L;

    @Parameter(hidden = true)
    private Long id;

    @NotBlank(message = FIRST_NAME_NOT_NULL_MESSAGE)
    private String firstName;

    private String lastName;

    @NotNull(message = IDENTIFICATION_TYPE_ID_NOT_NULL_MESSAGE)
    @Min(value = 1L, message = IDENTIFICATION_TYPE_ID_GREATER_THAN_ZERO_MESSAGE)
    private Integer identificationTypeId;

    @NotBlank(message = IDENTIFICATION_NUMBER_NOT_NULL_MESSAGE)
    private String identificationNumber;

    @NotBlank(message = EMAIL_NOT_NULL_MESSAGE)
    private String email;
}
