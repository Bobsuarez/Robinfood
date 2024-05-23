package com.robinfood.configurations.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriteriaCommonDTO {
    @Size(
        min = 3,
        message = "Minimum length is 3 characters"
    )
    private String search;
}
