package com.robinfood.core.dtos.transactionrequestdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.io.Serializable;

import static com.robinfood.core.constants.RegexConstants.REGEX_VALIDATOR_EMAIL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThirdPartyDTO implements Serializable {

    @NotBlank(message = "Document number is mandatory")
    private String documentNumber;

    @NotNull(message = "DocumentType is mandatory")
    @Positive(message = "Must be positive number")
    private Long documentType;

    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = REGEX_VALIDATOR_EMAIL, message = "The email is not valid")
    private String email;

    @NotBlank(message = "fullName is mandatory")
    private String fullName;

    private String phone;

}
