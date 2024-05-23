package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import static com.robinfood.core.constants.RegexConstants.REGEX_NOT_CONTAIN_SPECIAL_CHARACTERS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderFiscalIdentifierDTO implements Serializable {

    @JsonProperty("identifier")
    @Pattern(regexp = "^[\\w-]*$",
            message = REGEX_NOT_CONTAIN_SPECIAL_CHARACTERS)
    @NotBlank
    private String fiscalIdentifier;
}
